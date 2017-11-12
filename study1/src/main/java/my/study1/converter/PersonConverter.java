package my.study1.converter;

import com.google.gson.*;
import my.study1.entity.Contact;
import my.study1.entity.Person;
import my.study1.utils.DateUtils;

import java.lang.reflect.Type;

import static my.study1.converter.ConverterUtils.keyToObject;

public class PersonConverter implements JsonSerializer<Person>, JsonDeserializer<Person> {
    public JsonElement serialize(Person src, Type type,
                                 JsonSerializationContext context) {
        JsonObject person = new JsonObject();
        person.addProperty("fio", src.getFio());
        person.addProperty("birthDate", DateUtils.timeToString(src.getBirthDate()));
        if (src.getContact() != null) {
            JsonObject contact = new JsonObject();
            contact.addProperty("phoneMobile", src.getContact().getPhoneMobile());
            contact.addProperty("addressReg", src.getContact().getAddressReg());
            contact.addProperty("addressAct", src.getContact().getAddressAct());
            person.add("contact", contact);
        }
        return person;
    }

    public Person deserialize(JsonElement json, Type type,
                              JsonDeserializationContext context) throws JsonParseException {
        JsonObject personJson = json.getAsJsonObject();
        Person person = new Person((String) keyToObject(personJson.get("fio")),
                DateUtils.stringToTime((String) keyToObject(personJson.get("birthDate"))), null);
        JsonObject contactJson = personJson.getAsJsonObject("contact");
        if (!contactJson.isJsonNull()) {
            Contact contact = new Contact((String) keyToObject(contactJson.get("phoneMobile")),
                    (String) keyToObject(contactJson.get("addressReg")),
                    (String) keyToObject(contactJson.get("addressAct")));
            person.setContact(contact);
        }
        return person;
    }

}
