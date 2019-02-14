package model;

import bags.MasterCourseBag;
import bags.PeopleBag;
import bags.TextbookBag;

//highest level class

public class College {

	private PeopleBag peopleBag;
	private TextbookBag textbookBag;
	private MasterCourseBag masterCourseBag;
	
	private final int PEOPLEBAG_MAXSIZE = 50;
	private final int TEXTBOOKBAG_MAXSIZE = 50;
	private final int MASTERCOURSEBAG_MAXSIZE = 50;
	
	public College() {
		peopleBag = new PeopleBag(PEOPLEBAG_MAXSIZE);
		textbookBag = new TextbookBag(TEXTBOOKBAG_MAXSIZE);
		masterCourseBag = new MasterCourseBag(MASTERCOURSEBAG_MAXSIZE);
		load();
	}
	
	public void save() {
		masterCourseBag.save();
		textbookBag.save();
		peopleBag.save();
	}
	
	public void load() {
		masterCourseBag.load();
		textbookBag.load();
		peopleBag.load();
	}
	
	public void importAll() {
		masterCourseBag.importData();
		textbookBag.importData();
		peopleBag.importData();
	}

	public void exportAll() {
		masterCourseBag.exportData();
		textbookBag.exportData();
		peopleBag.exportData();
	}

	public PeopleBag getPeopleBag() {
		return peopleBag;
	}

	public void setPeopleBag(PeopleBag peopleBag) {
		this.peopleBag = peopleBag;
	}

	public TextbookBag getTextbookBag() {
		return textbookBag;
	}

	public void setTextbookBag(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
	}

	public MasterCourseBag getMasterCourseBag() {
		return masterCourseBag;
	}

	public void setMasterCourseBag(MasterCourseBag masterCourseBag) {
		this.masterCourseBag = masterCourseBag;
	}

}
