package my.study1;

import my.study1.dataFiller.DbFiller;
import my.study1.entity.Person;
import my.study1.utils.GenMode;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private HibernateTemplate hibernateTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-contex.xml");
        if (GenMode.GEN_ON.toString().equals(args[0])) {
            DbFiller dbFiller = (DbFiller) context.getBean("dbFiller");
            dbFiller.execute();
        }
        Main main = (Main) context.getBean("main");
        main.execute();
    }

    @Transactional
    public void execute() {
        printPersons(((List<Person>) hibernateTemplate.find("from Person")));
    }

    private void printPersons(List<Person> persons) {
        LOGGER.warn("RESULTS:");
        for (Person person : persons) {
            LOGGER.warn(person.toString() + "\n");
        }
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
