import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatientDAOlmpl implements PatientDAO{
    //connect database
    public static String driverName = "org.sqlite.JDBC";
    public static String url ="jdbc:sqlite:D:/Solfware/got/hospital.sqlte";
    public static Connection conn = null;
    //constant operators
//CRUD
    public static final String GET_ALL_PT = "select * from Patient";
    public static final String ADD_PT = "insert into Patient" + "(p_id, p_name, p_gender,p_age,p_address,p_blood_result) values (?,?,?,?)";
    public static final String UPDATE_PT = "update Patient set" + " p_name = ?, p_gender = ?, p_age = ?, p_address = ?, p__blood_result = ? where p_id = ?";
    public static final String DELETE_PT = "delete from Patient" + " where p_id = ?";
    public static final String FIND_PT_BY_ID = "select * from Patient" + " where p_id = ?";
    //create class instant
    private static  PatientDAOlmpl instant = new PatientDAOlmpl();
    public static PatientDAOlmpl getInstance(){
        return instant; }
    //constructor
    public PatientDAOlmpl() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Load Driver Successfully.");
    }
    @Override
    public List<Patient> getAllEmp() {
        List<Patient> PT =new ArrayList<Patient>();

        try {
            conn = PatientManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_PT);
            while (rs.next()){
                int p_id = rs.getInt(4);
                String p_name = rs.getString(50);
                String p_gender = rs.getString(10);
                int p_age = rs.getInt(2);
                String p_address = rs.getString(50);
                String p_blood_result = rs.getString(20);
                // add data to object
                PT.add(new Patient(p_id, p_name, p_gender, p_age, p_address , p_blood_result));
            }
            //close connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return PT;
    }

    @Override
    public void addPT(Patient newPT) {
        try {
            conn = PatientManagement.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(ADD_PT);
            //set parameter
            ps.setInt(1,newPT.getP_id());
            ps.setString(2,newPT.getP_name());
            ps.setString(3,newPT.getP_gender());
            ((PreparedStatement) ps).setInt(4,newPT.getP_age());
            ps.setString(4,newPT.getP_address());
            ps.setString(4,newPT.getP_blood_result());

            boolean rs = ps.execute();
            if (rs == true) {
                System.out.println("Could not add data to database.");
                System.exit(1);
            }
            System.out.println("Already add your data to database.");
            //close connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    @Override
    public void addEmp(Employee newEmp) {
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(ADD_EMP);
            //set parameter
            ps.setInt(1,newEmp.getEmpID());
            ps.setString(2,newEmp.getName());
            ps.setString(3,newEmp.getPosition());
            ps.setDouble(4,newEmp.getSalary());

            boolean rs = ps.execute();
            if (rs == true) {
                System.out.println("Could not add data to database.");
                System.exit(1);
            }
            System.out.println("Already add your data to databse.");
            //close connection
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePT(Patient Patient) {
        try {
            conn = PatientManagement.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(UPDATE_PI);
            //set parameter
            ps.setString(1,Patient.getP_name());
            ps.setInt(2,Patient.getP_age());
            ps.setString(3,Patient.getP_address());
            ps.setString(2,Patient.getP_gender());
            ps.setString(2,Patient.getP_blood_result());
            ps.setInt(4,Patient.getP_id());

            int rs = ps.executeUpdate();
            if (rs != 0) {
                System.out.println("Data with empID"
                        + Patient.getP_id() + "was update. ");
            } else {
                System.out.println("Cloud not update data with empID "
                        + Patient.getP_id());
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePI(int id) {
        try {
            conn = PatientManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(DELETE_PI);
            //set paremeter
            ps.setInt(1,id);
            int rs = ps.executeUpdate();
            if (rs != 0){
                System.out.println("Patient with p_id "
                        + id + "was deleted.");
            }else {
                System.out.println("could not delete Employee " +
                        "with empID "+ id);
            }
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Patient findPI(int id) {
        Patient PI = null;
        try {
            conn = PatientManagement.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(FIND_PT_BY_ID);
            //set parameter
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int p_id = rs.getInt(1);
                String p_name = rs.getString(2);
                String p_adress = rs.getString(3);
                int p_age = rs.getInt(1);
                String p_gender = rs.getString(3);
                String p_blood_result = rs.getString(3);

                emp = new Patient()p_id,p_name,p_gender,p_age,p_adress,p_blood_result);
            }else{
                System.out.println("cloud not found Patient " +
                        "with p_id "+id);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PT;
    }
}//class

