/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package staff;

import libaryInterfaces.StaffInterface;

/**
 *
 * @author M2200478
 */
public class Staff implements StaffInterface {

    private String firstName;
    private String lastName;
    private Address staffAddress;
    private int StaffID;

    public Staff() {
    }

    public Staff(String firstName, String lastName, Address staffAddress, int StaffID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.staffAddress = staffAddress;
        this.StaffID = StaffID;
    }

    public void DisplayStaffInfo() {
        Staff staff = new Staff(); //creates staff object
        staff.FirstName();
        staff.StaffID();
        staff.StaffAddress();
        staff.LastName();
    }
//Setting variables

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public void setStaffAddress(Address staffAddress) {
        this.staffAddress = staffAddress;
    }

    public void setStaffID(int staffID) {
        this.StaffID = staffID;
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getStaffAddress() {
        return staffAddress;
    }

    public int getStaffID() {
        return StaffID;
    }

    //Staff Interface Overide
    @Override
    public void FirstName() {
        System.out.println("");
    }

    @Override
    public void LastName() {
        System.out.println("");
    }

    @Override
    public void StaffID() {
        System.out.println(112475);
    }

    @Override
    public void StaffAddress() {
        System.out.println("");
    }

}

//private static final Scanner sc = new Scanner(System.in); 
//    private String name;
//    private String id;
//    private String address;
//
//    private void Staff_members(){
//        this.name = name;
//        this.id = id;
//        this.address = address;
//    }
//    
//    private static void Get_staff_name(){
//
//    }
//public class Staff implements StaffInterface{
////    private static final Scanner sc = new Scanner (System.in);
//    public void StaffInfo(){
//        
//    }
//           
//   
//    @Override
//    public void StaffName() {
//       System.out.println("Bob");
//    }
//
//
//    @Override
//    public void StaffID() {
//        System.out.println("Bob");
//    };
//
//    @Override
//    public void StaffAddress() {
//        System.out.println("17 Pennal Grove");
//    }
//         
//    
//}

