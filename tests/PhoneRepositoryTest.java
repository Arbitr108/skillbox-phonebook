import com.company.PhoneRepository;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Pavel Gorbatyuk
 * spec4web@gmail.com
 * on 01.05.2017.
 */
public class PhoneRepositoryTest {

    public static final String EXPORT_FILE = "export/test.json";
    PhoneRepository repository;

    @Before
    public void setUp() {
        repository = new PhoneRepository();
        fillRepository(repository);
    }

    @Test
    public void addTest() {
        Assert.assertEquals(4, repository.size());
    }

    @Test
    public void exportFileContainsCorrectJsonTest() throws IOException {
        File exportFile = new File(EXPORT_FILE);
        repository.export(exportFile);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(exportFile),
                StandardCharsets.UTF_8))) {
            Assert.assertEquals(getExpectedJsonString(), reader.readLine());
        }
    }

    private String getExpectedJsonString() {
        return "[{\"phone\":\"+79111111111\",\"name\":\"Pavel\"},{\"phone\":\"+79111111112\",\"name\":\"Sergey\"},{\"phone\":\"+79111111113\",\"name\":\"John\"},{\"phone\":\"+79111111114\",\"name\":\"Jane\"}]";
    }


    private void fillRepository(PhoneRepository phoneRepository) {
        phoneRepository.add("Pavel", "+79111111111");
        phoneRepository.add("Sergey", "+79111111112");
        phoneRepository.add("John", "+79111111113");
        phoneRepository.add("Jane", "+79111111114");
    }
}
