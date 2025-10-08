package utility;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class YamlConfigReader {
    public static Map<String, Object> readYamlConfig(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream in = YamlConfigReader.class.getClassLoader().getResourceAsStream(filePath)) {
            return yaml.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read YAML config: " + filePath, e);
        }
    }
}
