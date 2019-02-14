import model.*;

import java.util.ArrayList;

import util.Utilities;
import bags.*;

//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY
//DO NOT USE, FOR DEVELOPMENT ONLY

public class Demo {
//String courseTitle, String crn, Textbook textbook, String credits
	
	public static void main(String[] args) {
		//College college = Utilities.college;
		MasterCourseBag mcb = new MasterCourseBag();
		PeopleBag pb = new PeopleBag();
		TextbookBag tbb = new TextbookBag();
		tbb.importData();
		mcb.importData();
		pb.importData();
		tbb.save();
		mcb.save();
		pb.save();
		System.out.println(pb);
		//college.getPeopleBag().importData();
		//college.getPeopleBag().save();
		//System.out.println(college.getPeopleBag());
		//college.getMasterCourseBag().exportData();
		//CourseBag cb = new CourseBag();
		//cb.add(new Course("Stats", "12342", new Textbook(), 3.0, "ip"));
		//cb.save();
		//cb.load();
		//System.out.println(cb);


	}
	
	
	
	
	
	public static void mainGarbg(String[] args) {
		MasterCourseBag mcb = new MasterCourseBag();
		mcb.importData();
		CourseBag cb1 = new CourseBag();
		Course c = mcb.find("80897");
		c.setType("ip");
		c.setLetterGrade("A");
		Course course1 = new Course("Object Oriented Programming", "91923", new Textbook(), 4.0, "taken", "A");
		Course course2 = new Course("Statistics I", "498452", new Textbook("Stats book", "Sam Sample", "Publisher", "13-49248", 110.95), 3.0, "taken", "B+");
		Course course3 = new Course("English", "52353", new Textbook("book", "Sample", "Publisher", "13-46677", 190.95), 2.0, "taken", "C+");
		cb1.add(course1, course2, course3, c);
		//Course[] courses = { course1, course2, course3 };
		//cb1.add(course2);
		//cb1.add(course3);
		Student s = new Student("Corey", "Schneider", "631-123-4567", "Computer Sci", cb1);
		//System.out.println(s);
		//System.out.println(cb1.getCourseTakenArrayString());
		//cb1.add("80897", "taken");
		//System.out.println(s);
		//cb1.add(new Course("Object Oriented Programming", "91923", new Textbook(), 4.0), "ip");
		//
		//cb1.add(new Course("Secure job", "213902", new Textbook(), 4.0), "future");
		
		CourseBag cb2 = new CourseBag();
		cb2.add(course3);
		//cb2.add("ok", "taken");
		//cb2.add(new Course("Gym class cuz college", "75444", new Textbook(), 1.0), "ip");
		//cb2.add(new Course("Summer is almost here", "677221", new Textbook(), 4.0), "future");
		
		Student s2 = new Student("Bob", "Scider", "516-238-5858", "Liberal Arts", cb2);
		//System.out.println(s2);
		Person f1 = new Faculty("Bill", "Nye", "123-445-5829", "Professor", 45000);
		//System.out.println(f1);
		
		TextbookBag tbb = new TextbookBag();
		tbb.add(new Textbook("Computer book", "Sam Sample", "Big Publisher", "13-387123958", 120.95));
		tbb.add(new Textbook("Java for Starters", "Chef Boyardee", "Large Publisher", "13-12345678", 82.95));
		//System.out.println(tbb.find("13-12345678"));
		//System.out.println(tbb);
		//tbb.save();
		
		TextbookBag tbb2 = new TextbookBag();
		tbb2.add(new Textbook("Book for 2", "juanvier", "publisher2", "83-485823", 111.95));
		tbb2.add(new Textbook("Random name", "puerto rican author", "publisher2-2", "84-81237", 66.95));
		//System.out.println(tbb2);
		//tbb2.save();

		//tbb.load();
		//tbb2.load();
		
		PeopleBag pb = new PeopleBag();
		//System.out.println(cb1.find("chicken"));
		//s.setGpa(4.5);
		//pb.add(s, s2, f1);
		//System.out.println(pb);
		
		
		ArrayList<Person> al = new ArrayList<>();
		al.add(s);
		al.add(s2);
		
		
		//al.add(f1);
		/*
		System.out.println("pb content: "+pb);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++ SAVING:");
		pb.save2();
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++ LOADING:");
		pb.load2();*/
		
		tbb2.save();
		tbb2.load();
		
		
		
		//System.out.println("-------------------------------------------");
		//cb1.save();
		//System.out.println("-------------------------------------xxxxxx");
		//cb1.load();
		//pb.add(f1);
		//pb.exportData();
		
		
		
//		System.out.println(pb);
		//pb.exportData();
		//System.out.println(pb);
		//tbb.importData();
		//System.out.println(tbb);
		//tbb.exportData();
		
		////System.out.println(cb1.find("Statistics I"));
		//MasterCourseBag mcb = new MasterCourseBag();
		//System.out.println(mcb.find("90434"));
		//System.out.println(mcb);
		//mcb.exportData();
		
		//System.out.println(pb);
	}

}
