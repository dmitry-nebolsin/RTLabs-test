package ru.rtlabs.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import ru.rtlabs.entity.*;
import ru.rtlabs.repository.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** Сервис, предоставляющий доступ к данным задачи.
 */
@org.springframework.stereotype.Service
public class ReportSourceImpl implements ReportSource {

    private final ClaimRepository claimRepository;
    private final PersonRepository personRepository;
    private final ServiceRepository serviceRepository;


    @Autowired
    public ReportSourceImpl(ClaimRepository claimRepository, PersonRepository personRepository, ServiceRepository serviceRepository) {
        this.claimRepository = claimRepository;
        this.personRepository = personRepository;
        this.serviceRepository = serviceRepository;
    }

    /** Метод, заливающий в базу набор рандомизированных данных
     */
    @Override
    public void populate() {

        //Добавим 3 заявителя
        final List<Person> persons = Arrays.asList(
                new Person("Василий Петрович","ИП"),
                new Person("Петр Леонидович","ФЛ"),
                new Person("Никанор Васильевич","ЮЛ")
        );

        personRepository.deleteAll();

        //сохраним их, чтобы получить ID для вставки связных объектов
        List<Person> insertedPersons = personRepository.save(persons);

        //Сохраним 3 сервиса с подсервисами и ведомствами
        final List<Service> services = Arrays.asList(
                new Service("Уборка номера","12345",
                        "ФЛ",
                        Arrays.asList(new Subservice("CODE1", "Влажная уборка")),
                        new Department("Департамент клининга","CLEANING")),
                new Service("Кофе в постель","65432",
                        "ЮЛ",
                        Arrays.asList(new Subservice("CODE2", "Латте с корицей")),
                        new Department("Еда в номер","FOOD")),
                new Service("Такси","838383",
                        "ИП",
                        Arrays.asList(new Subservice("CODE3", "Такси категории люкс")),
                        new Department("Департамент перевозок","DRIVE"))
        );

        serviceRepository.deleteAll();

        List<Service> insertedServices = serviceRepository.save(services);

        Random r = new Random();

        claimRepository.deleteAll();

        //Зальём 200 заявок со случайными заявителями, датами и сервисами
        for (int i = 0;i<200;i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, r.nextInt(30));
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            claimRepository.save(new Claim(String.valueOf(i), insertedServices.get(r.nextInt(3)).getId(), insertedPersons.get(r.nextInt(3)).getId(), cal.getTime(), "NEW"));
        }
    }

    /** Метод получения данных отчёта.
     * Скорее всего можно данные подтянуть одним запросом, не обращаясь каждый раз за связанными объектами,
     * если бы это был SQL я бы так и сделал, пока обойдёмся тем, что хотя бы закешируем заявителей и сервисы
     */
    @Override
    public List<ReportRow> getReportRows() {
        List<ReportRow> result = new ArrayList<>();

        List<Claim> claims = claimRepository.findAll();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        //кеши для заявителей и сервисов
        Map<String,Person> persons = new HashMap<>();
        Map<String,Service> services = new HashMap<>();

        for (Claim c : claims) {
            //получаем связные объекты, сначала проверив кеш
            Person person = persons.get(c.getPersonId());
            if (person == null) {
                person = personRepository.findOne(c.getPersonId());
                persons.put(person.getId(),person);
            }

            Service service = services.get(c.getServiceId());
            if (service == null) {
                service = serviceRepository.findOne(c.getServiceId());
                services.put(service.getId(),service);
            }

            String subserviceNames = "";

            //Подуслуги в теории один-ко-многим, так что соберём их названия через запятую
            if (service.getSubservices() != null)
                for (Subservice subservice : service.getSubservices()) {
                    subserviceNames += subservice.getName() + ',';
                }
            if (subserviceNames != null && !"".equals(subserviceNames)) subserviceNames = subserviceNames.substring(0,subserviceNames.length()-1);

            ReportRow row = new ReportRow();
            row.setOrderNumber(c.getOrderId());
            row.setDate(df.format(c.getDate()));
            row.setFormCode(service.getFormCode());
            row.setStatus(c.getStatus());
            row.setFio(person.getFio());
            row.setType(person.getOrgType());
            row.setServiceName(service.getTargetCode());
            row.setSubserviceName(subserviceNames);

            if (service.getDepartment() != null)
                row.setEnterpriseName(service.getDepartment().getName());

            result.add(row);
        }
        return result;
    }

    /** Получение данных для гистограммы
     * Возвращает TreeMap потому что он сразу отсортирован по ключам - удобно выводить
     * Опять таки, скорее всего возможно как-то отфильтровать по значению поля связного
     * объекта и сагрегировать по датам сразу на стороне базы - но я пока что получу агрегацию вручную.
     */
    @Override
    public TreeMap<Date, Integer> getBarSeries(String type) {
        TreeMap<Date, Integer> result = new TreeMap<>();
        Map<String,Person> persons = new HashMap<>();

        List<Claim> claims = claimRepository.findAll();

        for (Claim c : claims) {
            Person person = persons.get(c.getPersonId());
            if (person == null) {
                person = personRepository.findOne(c.getPersonId());
                persons.put(person.getId(),person);
            }

            //Если нужный идентификатор - проверяем наличие даты в карте,
            //если она есть - увеличиваем счётчик, если нет -
            //добавляем в карту со значением 1
            if (type.equals(person.getOrgType())) {
                Integer count = result.get(c.getDate());
                if (count == null) result.put(c.getDate(),1);
                else result.put(c.getDate(),++count);
            }
        }

        return result;
    }

}
