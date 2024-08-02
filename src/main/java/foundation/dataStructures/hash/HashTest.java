package foundation.dataStructures.hash;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class HashTest {
    public static void main(String[] args) {
        HashTable table = new HashTable();
        int hash = Hashing.murmur3_32().hashString("abc", StandardCharsets.UTF_8).asInt();
        System.out.println(hash);
        table.print();

    }
}
