package org.hisand.bible;

import java.sql.Connection;

import org.hisand.book.dao.TestDAO;
import org.hisand.db.ConnectionHelper;
import org.hisand.db.DBHelper;

public class ExportorManager {
	
	
	String re_zhang[] = new String[]{
			"Judges 5",
			"Judges 6",
			"Judges 7",
			"Judges 8",
			"Judges 9",
			"Judges 10",
			"Judges 11",
			"Judges 12",
			"Judges 13",
			"Judges 14",
			"Judges 15",
			"Judges 16",
			"Judges 17",
			"Judges 18",
			"Judges 19",
			"Judges 20",
			"Judges 21",
			"1 Kings 6",
			"1 Kings 7",
			"1 Kings 8",
			"1 Kings 9",
			"1 Kings 10",
			"1 Kings 11",
			"1 Kings 12",
			"1 Kings 13",
			"1 Kings 14",
			"1 Kings 15",
			"1 Kings 16",
			"1 Kings 17",
			"1 Kings 18",
			"1 Kings 19",
			"1 Kings 20",
			"1 Kings 21",
			"1 Kings 22",
			"Psalms 51",
			"Psalms 52",
			"Psalms 53",
			"Psalms 54",
			"Psalms 55",
			"Psalms 56",
			"Psalms 57",
			"Psalms 58",
			"Psalms 59",
			"Psalms 60",
			"Psalms 61",
			"Psalms 62",
			"Psalms 63",
			"Psalms 64",
			"Psalms 65",
			"Psalms 66",
			"Psalms 67",
			"Psalms 68",
			"Psalms 69",
			"Psalms 70",
			"Psalms 71",
			"Psalms 72",
			"Psalms 73",
			"Psalms 74",
			"Psalms 75",
			"Psalms 76",
			"Psalms 77",
			"Psalms 78",
			"Psalms 79",
			"Psalms 80",
			"Psalms 81",
			"Psalms 82",
			"Psalms 83",
			"Psalms 84",
			"Psalms 85",
			"Psalms 86",
			"Psalms 87",
			"Psalms 88",
			"Psalms 89",
			"Psalms 90",
			"Psalms 91",
			"Psalms 92",
			"Psalms 93",
			"Psalms 94",
			"Psalms 95",
			"Psalms 96",
			"Psalms 97",
			"Psalms 98",
			"Psalms 99",
			"Psalms 100",
			"Psalms 101",
			"Psalms 102",
			"Psalms 103",
			"Psalms 104",
			"Psalms 105",
			"Psalms 106",
			"Psalms 107",
			"Psalms 108",
			"Psalms 109",
			"Psalms 110",
			"Psalms 111",
			"Psalms 112",
			"Psalms 113",
			"Psalms 114",
			"Psalms 115",
			"Psalms 116",
			"Psalms 117",
			"Psalms 118",
			"Psalms 119",
			"Psalms 120",
			"Psalms 121",
			"Psalms 122",
			"Psalms 123",
			"Psalms 124",
			"Psalms 125",
			"Psalms 126",
			"Psalms 127",
			"Psalms 128",
			"Psalms 129",
			"Psalms 130",
			"Psalms 131",
			"Psalms 132",
			"Psalms 133",
			"Psalms 134",
			"Psalms 135",
			"Psalms 136",
			"Psalms 137",
			"Psalms 138",
			"Psalms 139",
			"Psalms 140",
			"Psalms 141",
			"Psalms 142",
			"Psalms 143",
			"Psalms 144",
			"Psalms 145",
			"Psalms 146",
			"Psalms 147",
			"Psalms 148",
			"Psalms 149",
			"Psalms 150",
			"Isaiah 51",
			"Isaiah 52",
			"Isaiah 53",
			"Isaiah 54",
			"Isaiah 55",
			"Isaiah 56",
			"Isaiah 57",
			"Isaiah 58",
			"Isaiah 59",
			"Isaiah 60",
			"Isaiah 61",
			"Isaiah 62",
			"Isaiah 63",
			"Isaiah 64",
			"Isaiah 65",
			"Isaiah 66",
			"Jeremiah 51",
			"Jeremiah 52",
			"Romans 14",
			"Romans 15",
			"Romans 16",
			"1 Corinthians 1",
			"1 Corinthians 2",
			"1 Corinthians 3",
			"1 Corinthians 4",
			"1 Corinthians 5",
			"1 Corinthians 6",
			"1 Corinthians 7",
			"1 Corinthians 8",
			"1 Corinthians 9",
			"1 Corinthians 10",
			"1 Corinthians 11",
			"1 Corinthians 12",
			"1 Corinthians 13",
			"1 Corinthians 14",
			"1 Corinthians 15",
			"1 Corinthians 16",
			"2 Corinthians 1",
			"2 Corinthians 2",
			"2 Corinthians 3",
			"2 Corinthians 4",
			"2 Corinthians 5",
			"2 Corinthians 6",
			"2 Corinthians 7",
			"2 Corinthians 8",
			"2 Corinthians 9",
			"2 Corinthians 10",
			"2 Corinthians 11",
			"2 Corinthians 12",
			"2 Corinthians 13",
			"Galatians 1",
			"Galatians 2",
			"Galatians 3",
			"Galatians 4",
			"Galatians 5",
			"Galatians 6",
			"Ephesians 1",
			"Ephesians 2",
			"Ephesians 3",
			"Ephesians 4",
			"Ephesians 5",
			"Ephesians 6",
			"Philippians 1",
			"Philippians 2",
			"Philippians 3",
			"Philippians 4",
			"Colossians 1",
			"Colossians 2",
			"Colossians 3",
			"Colossians 4",
			"1 Thessalonians 1",
			"1 Thessalonians 2",
			"1 Thessalonians 3",
			"1 Thessalonians 4",
			"1 Thessalonians 5",
			"2 Thessalonians 1",
			"2 Thessalonians 2",
			"2 Thessalonians 3",
			"1 Timothy 1",
			"1 Timothy 2",
			"1 Timothy 3",
			"1 Timothy 4",
			"1 Timothy 5",
			"1 Timothy 6",
			"2 Timothy 1",
			"2 Timothy 2",
			"2 Timothy 3",
			"2 Timothy 4",
			"Titus 1",
			"Titus 2",
			"Titus 3",
			"Philemon 1",
			"Hebrews 1",
			"Hebrews 2",
			"Hebrews 3",
			"Hebrews 4",
			"Hebrews 5",
			"Hebrews 6",
			"Hebrews 7",
			"Hebrews 8",
			"Hebrews 9",
			"Hebrews 10",
			"Hebrews 11",
			"Hebrews 12",
			"Hebrews 13",
			"James 1",
			"James 2",
			"James 3",
			"James 4",
			"James 5",
			"1Peter 1",
			"1Peter 2",
			"1Peter 3",
			"1Peter 4",
			"1Peter 5",
			"2Peter 1",
			"2Peter 2",
			"2Peter 3",
			"1 John 1",
			"1 John 2",
			"1 John 3",
			"1 John 4",
			"1 John 5",
			"2 John 1",
			"3 John 1",
			"Jude 1",
			"Revelation 1",
			"Revelation 2",
			"Revelation 3",
			"Revelation 4",
			"Revelation 5",
			"Revelation 6",
			"Revelation 7",
			"Revelation 8",
			"Revelation 9",
			"Revelation 10",
			"Revelation 11",
			"Revelation 12",
			"Revelation 13",
			"Revelation 14",
			"Revelation 15",
			"Revelation 16",
			"Revelation 17",
			"Revelation 18",
			"Revelation 19",
			"Revelation 20",
			"Revelation 21",
			"Revelation 22",	
	};
	
	
	String[] indexsOld = new String[]{
			"Gen_1.html,创世记,Genesis,50",
			"Exo_1.html,出埃及记,Exodus,40",
			"Lev_1.html,利未记,Leviticus,27",
			"Num_1.html,民数记,Numbers,36",
			"Deu_1.html,申命记,Deuteronomy,34",
			"Jos_1.html,约书亚记,Joshua,24",
			"Jug_1.html,士师记,Judges,21",
			"Rut_1.html,路得记,Ruth,4",
			"1Sa_1.html,撒母耳记上,1 Samuel,31",
			"2Sa_1.html,撒母耳记下,2 Samuel,24",
			"1Ki_1.html,列王记上,1 Kings,22",
			"2Ki_1.html,列王记下,2 Kings,25",
			"1Ch_1.html,历代记上,1 Chronicles,29",
			"2Ch_1.html,历代记下,2 Chronicles,36",
			"Ezr_1.html,以斯拉记,Ezra,10",
			"Neh_1.html,尼希米记,Nehemiah,13",
			"Est_1.html,以斯帖记,Esther,10",
			"Job_1.html,约伯记,Job,42",
			"Psm_1.html,诗篇,Psalms,150",
			"Pro_1.html,箴言,Proverbs,31",
			"Ecc_1.html,传道书,Ecclesiastes,12",
			"Son_1.html,雅歌,Song of Songs,8",
			"Isa_1.html,以赛亚书,Isaiah,66",
			"Jer_1.html,耶利米书,Jeremiah,52",
			"Lam_1.html,耶利米哀歌,Lamentations,5",
			"Eze_1.html,以西结书,Ezekiel,48",
			"Dan_1.html,但以理书,Daniel,12",
			"Hos_1.html,何西阿书,Hosea,14",
			"Joe_1.html,约珥书,Joel,3",
			"Amo_1.html,阿摩司书,Amos,9",
			"Oba_1.html,俄巴底亚书,Obadiah,1",
			"Jon_1.html,约拿书,Jonah,4",
			"Mic_1.html,弥迦书,Micah,7",
			"Nah_1.html,那鸿书,Nahum,3",
			"Hab_1.html,哈巴谷书,Habakkuk,3",
			"Zep_1.html,西番雅书,Zephaniah,3",
			"Hag_1.html,哈该书,Haggai,2",
			"Zec_1.html,撒迦利亚书,Zechariah,14",
			"Mal_1.html,玛拉基书,Malachi,4"};
	
	String[] indexsNew = new String[]{
			"Mat_1.html,马太福音,Matthew,28",
			"Mak_1.html,马可福音,Mark,16",
			"Luk_1.html,路加福音,Luke,24",
			"Jhn_1.html,约翰福音,John,21",
			"Act_1.html,使徒行传,Acts,28",
			"Rom_1.html,罗马书,Romans,16",
			"1Co_1.html,哥林多前书,1 Corinthians,16",
			"2Co_1.html,哥林多后书,2 Corinthians,13",
			"Gal_1.html,加拉太书,Galatians,6",
			"Eph_1.html,以弗所书,Ephesians,6",
			"Phl_1.html,腓立比书,Philippians,4",
			"Col_1.html,歌罗西书,Colossians,4",
			"1Ts_1.html,帖撒罗尼迦前书,1 Thessalonians,5",
			"2Ts_1.html,帖撒罗尼迦后书,2 Thessalonians,3",
			"1Ti_1.html,提摩太前书,1 Timothy,6",
			"2Ti_1.html,提摩太后书,2 Timothy,4",
			"Tit_1.html,提多书,Titus,3",
			"Phm_1.html,腓利门书,Philemon,1",
			"Heb_1.html,希伯来书,Hebrews,13",
			"Jas_1.html,雅各书,James,5",
			"1Pe_1.html,彼得前书,1Peter,5",
			"2Pe_1.html,彼得后书,2Peter,3",
			"1Jn_1.html,约翰一书,1 John,5",
			"2Jn_1.html,约翰二书,2 John,1",
			"3Jn_1.html,约翰三书,3 John,1",
			"Jud_1.html,犹大书,Jude,1",
			"Rev_1.html,启示录,Revelation,22"};
	

	private TestDAO fromTestDAO = null;
	private TestDAO toTestDAO = null;
	
	private Connection fromConnection = null;
	private Connection toConnection = null;
	
	public ExportorManager() throws Exception {
		fromConnection = ConnectionHelper.getConnection("book");
		toConnection = ConnectionHelper.getConnection("main");
		
		fromTestDAO = new TestDAO();
		fromTestDAO.setConnection(fromConnection, "book");
		
		toTestDAO = new TestDAO();
		toTestDAO.setConnection(toConnection, "main");
	}
	
	public void closeConnection() {
		DBHelper.close(fromConnection);
		DBHelper.close(toConnection);
	}
	
	public void createTOCOfBible() throws Exception {
		try {
			BibleCreator bc = new BibleCreator();
			int old_parent[] = bc.createOldTestament();
			int new_parent[] = bc.createNewTestament();
			
			int parent[] = null;
			String[] indexs = null;
			
			{
				parent = old_parent;
				indexs = indexsOld;
				for (int index = 0; index < indexs.length; index++) {
					String link = indexs[index];
					String[] arr = link.split(",");
					String cnname = arr[1];
					String enname = arr[2];
					String ref_id = enname;
					bc.createFolder(parent[0], cnname, enname, ref_id);
				}
			}
			
			{
				parent = new_parent;
				indexs = indexsNew;
				for (int index = 0; index < indexs.length; index++) {
					String link = indexs[index];
					String[] arr = link.split(",");
					String cnname = arr[1];
					String enname = arr[2];
					String ref_id = enname;
					bc.createFolder(parent[0], cnname, enname, ref_id);
				}
			}
			
			{
				parent = old_parent;
				indexs = indexsOld;
				for (int index = 0; index < indexs.length; index++) {
					String link = indexs[index];
					String[] arr = link.split(",");
					String cnname = arr[1];
					String enname = arr[2];
					int count = Integer.parseInt(arr[3]);
					String ref_id = enname;
					int pianinfo[] = bc.createFolder(parent[0], cnname, enname, ref_id);
					for (int i = 0; i < count; i++) {
						int sort_order = i + 1;
						String zhang_ref_id = enname + " "+ sort_order;
						String zhang_enname = "Chapter " + sort_order;
						String zhang_cnname = "第 " + sort_order + " 章";
						bc.createFolder(pianinfo[0], zhang_cnname, zhang_enname, zhang_ref_id);
						
					}
				}
			}
			
			{
				parent = new_parent;
				indexs = indexsNew;
				for (int index = 0; index < indexs.length; index++) {
					String link = indexs[index];
					String[] arr = link.split(",");
					String cnname = arr[1];
					String enname = arr[2];
					int count = Integer.parseInt(arr[3]);
					String ref_id = enname;
					int pianinfo[] = bc.createFolder(parent[0], cnname, enname, ref_id);
					for (int i = 0; i < count; i++) {
						int sort_order = i + 1;
						String zhang_ref_id = enname + " "+ sort_order;
						String zhang_enname = "Chapter " + sort_order;
						String zhang_cnname = "第 " + sort_order + " 章";
						bc.createFolder(pianinfo[0], zhang_cnname, zhang_enname, zhang_ref_id);
						
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("createIndex First error!" + e.getMessage());
			throw e;
		}
	}
	
	private boolean isReZhang(String value) {
		for (String s : re_zhang) {
			if (value.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	public void create() throws Exception {
		BibleCreator bc = new BibleCreator();
		int old_parent[] = bc.createOldTestament();
		int new_parent[] = bc.createNewTestament();
		
		int parent[] = null;
		String[] indexs = null;
		
		parent = old_parent;
		indexs = indexsOld;
		create(parent, indexs);
		
		parent = new_parent;
		indexs = indexsNew;
		create(parent, indexs);
	}
	
	private void create(int parent[], String[] indexs) throws Exception {
		BibleCreator bc = new BibleCreator();
	
		for (int index = 0; index < indexs.length; index++) {
			String link = indexs[index];
			String[] arr = link.split(",");
			String cnname = arr[1];
			String enname = arr[2];
			int count = Integer.parseInt(arr[3]);
			String ref_id = enname;
			
			int pianinfo[] = bc.createFolder(parent[0], cnname, enname, ref_id);
			
			for (int i = 0; i < count; i++) {
				int sort_order = i + 1;
				String zhang_ref_id = enname + " "+ sort_order;
				String zhang_enname = "Chapter " + sort_order;
				String zhang_cnname = "第 " + sort_order + " 章";
				
				if (isReZhang(zhang_ref_id)) {
					System.out.println("not process re_zhang '" + zhang_ref_id + "'");
				}
				else {
					int[] zhang_parent = bc.createFolder(pianinfo[0], zhang_cnname, zhang_enname, zhang_ref_id);
					createJie(zhang_parent, zhang_ref_id, i + 1);
					
				}
			}
		}
	}
	
	private void createJie(int[] zhang_parent, String zhang_ref_id, int zhang_sort_id) throws Exception {
		BibleCreator bc = new BibleCreator();
		int zhang_child_count = toTestDAO.getCountThis(zhang_parent[0]);
		if (zhang_child_count == 0) {
			System.out.println("not process zhang '" + zhang_ref_id + "',because count is 0!");
			return;
		}
		
		for (int i = 0; i < zhang_child_count; i++) {
			int sort_id = i + 1;
			String ref_id = zhang_ref_id + ":" + sort_id;
			String name = zhang_sort_id + ":" + sort_id;
			
			String[] contentarr = fromTestDAO.getContentByRefId(ref_id);
			
			if (contentarr == null) {
				System.out.println("\tnot process jie '" + ref_id + "',because no content!");
			}
			else {
				String cnname = name;
				String enname = ref_id;
				String cncontent = contentarr[0];
				String encontent = contentarr[1];
				bc.createArticle(zhang_parent[0], cnname, enname, cncontent, encontent, ref_id);
				//System.out.println("\tprocess jie '" + ref_id + "'!");
			}
		}
		
		System.out.println("process zhang '" + zhang_ref_id + "'!");
	}
	
}
