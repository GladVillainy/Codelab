package Codelab_3;

import Codelab_3.entities.Member;
import Codelab_3.persistence.Database;
import Codelab_3.persistence.MemberMapper;
import Codelab_3.persistence.RegistrationMapper;

import java.util.List;

public class Main {

    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
    private final static String URL = "jdbc:postgresql://localhost:5432/sportsclub";

    public static void main(String[] args) {

        Database db = new Database(USER, PASSWORD, URL);
        MemberMapper memberMapper = new MemberMapper(db);
        RegistrationMapper rm = new RegistrationMapper(db);
        List<Member> members = memberMapper.getAllMembers();

       // showMembers(members);
       // showMemberById(memberMapper, 13);


        //memberMapper.teamParticipants();
        //memberMapper.seperatedByGender();
       // memberMapper.totalSumIncome();
        // memberMapper.averagePaymentEachTeam();

        rm.addToTeam(10, "yo02", 500);

        /*  
            int newMemberId = insertMember(memberMapper);
            deleteMember(newMemberId, memberMapper);
            showMembers(members);
            updateMember(13, memberMapper);
        */
    }

    private static void deleteMember(int memberId, MemberMapper memberMapper) {
        if (memberMapper.deleteMember(memberId)){
            System.out.println("Member with id = " + memberId + " is removed from DB");
        }
    }

    private static int insertMember(MemberMapper memberMapper) {
        Member m1 = new Member("Ole Olsen", "Banegade 2", 3700, "Rønne", "m", 1967);
        Member m2 = memberMapper.insertMember(m1);
        showMemberById(memberMapper, m2.getMemberId());
        return m2.getMemberId();
    }

    private static void updateMember(int memberId, MemberMapper memberMapper) {
        Member m1 = memberMapper.getMemberById(memberId);
        m1.setYear(1970);
        if(memberMapper.updateMember(m1)){
            showMemberById(memberMapper, memberId);
        }
    }

    private static void showMemberById(MemberMapper memberMapper, int memberId) {
        System.out.println("***** Vis medlem nr. 13: *******");
        System.out.println(memberMapper.getMemberById(memberId).toString());
    }

    private static void showMembers(List<Member> members) {
        System.out.println("***** Vis alle medlemmer *******");
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }
}
