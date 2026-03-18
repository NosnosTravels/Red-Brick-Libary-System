/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import book.dueDate;
import book.ISBN;
import members.memberID;
import book.dueDate;

/**
 *
 * @author M2200478
 */
public interface MemberRepository {

    void borrow(ISBN ISBN, memberID memberID, dueDate dueDate);
    void returnBook(ISBN isbn);
    void findActiveLoans();
    void findByMember(memberID memberID);
}
