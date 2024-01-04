import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

class Contact {
    private String name;
    private int age;
    private long phoneNumber;
    private String company;     // Pode ser null
    private ArrayList<String> emails;

    public Contact(String name, int age, long phoneNumber, String company, List<String> emails) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.emails = new ArrayList<>(emails);
    }

    public String name() { return name; }
    public int age() { return age; }
    public long phoneNumber() { return phoneNumber; }
    public String company() { return company; }
    public List<String> emails() { return new ArrayList(emails); }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(this.name);
        out.writeInt(this.age);
        out.writeLong(this.phoneNumber);
        if (this.company == null)
        {
            out.writeInt(0);
        }
        else
        {
            out.writeInt(1);
            out.writeUTF(this.company);
        }
        out.writeInt(this.emails.size());
        for (String email : this.emails)
        {
            out.writeUTF(email);
        }
    }

    public static Contact deserialize(DataInputStream in)
    {
        try {
            String name = in.readUTF();
            int age = in.readInt();
            long phoneNumber = in.readLong();
            int flag = in.readInt();
            String company = null;
            if (flag == 1)
            {
                company = in.readUTF();
            }
            int size = in.readInt();
            List<String> emails = new ArrayList<>();
            for (int i = 0; i < size; i++)
            {
                emails.add(in.readUTF());
            }
            return new Contact(name,age,phoneNumber,company,emails);
        } catch (IOException e)
        {
            return null;
        }

    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name).append(";");
        builder.append(this.age).append(";");
        builder.append(this.phoneNumber).append(";");
        builder.append(this.company).append(";");
        builder.append(this.emails.toString());
        builder.append("}");
        return builder.toString();
    }

}
