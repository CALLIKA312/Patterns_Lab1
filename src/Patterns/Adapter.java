package Patterns;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Adapter {

    public OutputStream convertStringToOS(String[] sa) {
        OutputStream os = new ByteArrayOutputStream(sa.length);
        for (String s : sa) {
            byte[] tmpBytes = s.getBytes(StandardCharsets.UTF_8);
            try {
                os.write(tmpBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return os;
    }
}
