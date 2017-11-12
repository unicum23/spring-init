package my.study1.dataFiller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import my.study1.converter.PersonConverter;
import my.study1.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DbFiller {
    private static final Logger LOGGER = Logger.getLogger(DbFiller.class);
    private static final String DATA_FILL_DIR = "dataFill";
    private static final Class[] FILL_MODELS = {Person.class};
    private static final Map<String, Object> CONVERTERS;

    static {
        CONVERTERS = new HashMap<>();
        CONVERTERS.put("Person", new PersonConverter());
    }

    private ClassLoader classLoader = getClass().getClassLoader();
    private HibernateTemplate hibernateTemplate;

    @Transactional
    public void execute() {
        for (Class clazz : FILL_MODELS) {
            generateModel(clazz);
        }
    }

    public void generateModel(Class clazz) {
        Object converter = CONVERTERS.get(clazz.getSimpleName());
        if (converter == null) {
            LOGGER.error("Need to define converter for model: " + clazz.getSimpleName());
            return;
        }
        try {
            File file = new File(classLoader.getResource(DATA_FILL_DIR + "/" + clazz.getSimpleName() + ".json").getFile());
            Gson gson = new GsonBuilder().registerTypeAdapter(clazz, converter).create();
            JsonReader reader = new JsonReader(new FileReader(file));
            Object[] records = gson.fromJson(reader, Class.forName("[L" + clazz.getName() + ";"));
            if (records.length != 0) {
                for (Object record : records) {
                    hibernateTemplate.save(record);
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File for data filling is not found", e);
        } catch (Exception ex) {
            LOGGER.error("Process autofilling DB was finished with error", ex);
        }
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
