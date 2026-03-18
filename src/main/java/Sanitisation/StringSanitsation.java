/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sanitisation;


import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

/**
 *
 * @author M2200478
 */
public class StringSanitsation {
    private static final PolicyFactory SANITISER_NO_HTML =
            new HtmlPolicyBuilder()
                    .allowElements(). //none allowed = strips all HTML tags
                    toFactory();
    
    private static final PolicyFactory SANITISER_BASIC = 
            new HtmlPolicyBuilder()
                    .allowElements()
                    .allowUrlProtocols("https")
                    .allowAttributes("href").onElements("a")
                    .toFactory(); 

    public static void OwaspStringSanitzeWithHTML(String[] args) {
        String input = "<script>alert('xss')</script><p>Hello <b> world</b></p>";
        String cleaned = SANITISER_BASIC.sanitize(input);
        System.out.println(cleaned);
        // Output: <p>Hellow <b>world</b></p>" 
    }
}