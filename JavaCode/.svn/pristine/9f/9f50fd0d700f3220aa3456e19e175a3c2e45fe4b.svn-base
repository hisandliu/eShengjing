package org.hisand.bible;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hisand.book.dao.TestDAO;
import org.hisand.core.WebCatcher;

public class CatchBibleFromTianyaBook {

	private String encoding = "gb2312";
	private String rooturl = "http://www.tianyabook.com/zongjiao/shengjing/";
	private String rooturl2 = "http://bible.kuanye.net/cnen/";

	
	String re_zhang[] = new String[] { 
			"Judges 5,Jug", "Judges 6,Jug",
			"Judges 7,Jug", "Judges 8,Jug", "Judges 9,Jug", "Judges 10,Jug",
			"Judges 11,Jug", "Judges 12,Jug", "Judges 13,Jug", "Judges 14,Jug",
			"Judges 15,Jug", "Judges 16,Jug", "Judges 17,Jug", "Judges 18,Jug",
			"Judges 19,Jug", "Judges 20,Jug", "Judges 21,Jug", "1 Kings 6,1Ki",
			"1 Kings 7,1Ki", "1 Kings 8,1Ki", "1 Kings 9,1Ki",
			"1 Kings 10,1Ki", "1 Kings 11,1Ki", "1 Kings 12,1Ki",
			"1 Kings 13,1Ki", "1 Kings 14,1Ki", "1 Kings 15,1Ki",
			"1 Kings 16,1Ki", "1 Kings 17,1Ki", "1 Kings 18,1Ki",
			"1 Kings 19,1Ki", "1 Kings 20,1Ki", "1 Kings 21,1Ki",
			"1 Kings 22,1Ki", "Psalms 51,Psm", "Psalms 52,Psm",
			"Psalms 53,Psm", "Psalms 54,Psm", "Psalms 55,Psm", "Psalms 56,Psm",
			"Psalms 57,Psm", "Psalms 58,Psm", "Psalms 59,Psm", "Psalms 60,Psm",
			"Psalms 61,Psm", "Psalms 62,Psm", "Psalms 63,Psm", "Psalms 64,Psm",
			"Psalms 65,Psm", "Psalms 66,Psm", "Psalms 67,Psm", "Psalms 68,Psm",
			"Psalms 69,Psm", "Psalms 70,Psm", "Psalms 71,Psm", "Psalms 72,Psm",
			"Psalms 73,Psm", "Psalms 74,Psm", "Psalms 75,Psm", "Psalms 76,Psm",
			"Psalms 77,Psm", "Psalms 78,Psm", "Psalms 79,Psm", "Psalms 80,Psm",
			"Psalms 81,Psm", "Psalms 82,Psm", "Psalms 83,Psm", "Psalms 84,Psm",
			"Psalms 85,Psm", "Psalms 86,Psm", "Psalms 87,Psm", "Psalms 88,Psm",
			"Psalms 89,Psm", "Psalms 90,Psm", "Psalms 91,Psm", "Psalms 92,Psm",
			"Psalms 93,Psm", "Psalms 94,Psm", "Psalms 95,Psm", "Psalms 96,Psm",
			"Psalms 97,Psm", "Psalms 98,Psm", "Psalms 99,Psm",
			"Psalms 100,Psm", "Psalms 101,Psm", "Psalms 102,Psm",
			"Psalms 103,Psm", "Psalms 104,Psm", "Psalms 105,Psm",
			"Psalms 106,Psm", "Psalms 107,Psm", "Psalms 108,Psm",
			"Psalms 109,Psm", "Psalms 110,Psm", "Psalms 111,Psm",
			"Psalms 112,Psm", "Psalms 113,Psm", "Psalms 114,Psm",
			"Psalms 115,Psm", "Psalms 116,Psm", "Psalms 117,Psm",
			"Psalms 118,Psm", "Psalms 119,Psm", "Psalms 120,Psm",
			"Psalms 121,Psm", "Psalms 122,Psm", "Psalms 123,Psm",
			"Psalms 124,Psm", "Psalms 125,Psm", "Psalms 126,Psm",
			"Psalms 127,Psm", "Psalms 128,Psm", "Psalms 129,Psm",
			"Psalms 130,Psm", "Psalms 131,Psm", "Psalms 132,Psm",
			"Psalms 133,Psm", "Psalms 134,Psm", "Psalms 135,Psm",
			"Psalms 136,Psm", "Psalms 137,Psm", "Psalms 138,Psm",
			"Psalms 139,Psm", "Psalms 140,Psm", "Psalms 141,Psm",
			"Psalms 142,Psm", "Psalms 143,Psm", "Psalms 144,Psm",
			"Psalms 145,Psm", "Psalms 146,Psm", "Psalms 147,Psm",
			"Psalms 148,Psm", "Psalms 149,Psm", "Psalms 150,Psm",
			"Isaiah 51,Isa", "Isaiah 52,Isa", "Isaiah 53,Isa", "Isaiah 54,Isa",
			"Isaiah 55,Isa", "Isaiah 56,Isa", "Isaiah 57,Isa", "Isaiah 58,Isa",
			"Isaiah 59,Isa", "Isaiah 60,Isa", "Isaiah 61,Isa", "Isaiah 62,Isa",
			"Isaiah 63,Isa", "Isaiah 64,Isa", "Isaiah 65,Isa", "Isaiah 66,Isa",
			"Jeremiah 51,Jer", "Jeremiah 52,Jer" };

	String[] indexs = new String[] { "Gen_1.html,创世记,Genesis,50",
			"Exo_1.html,出埃及记,Exodus,40", "Lev_1.html,利未记,Leviticus,27",
			"Num_1.html,民数记,Numbers,36", "Deu_1.html,申命记,Deuteronomy,34",
			"Jos_1.html,约书亚记,Joshua,24", "Jug_1.html,士师记,Judges,21",
			"Rut_1.html,路得记,Ruth,4", "1Sa_1.html,撒母耳记上,1 Samuel,31",
			"2Sa_1.html,撒母耳记下,2 Samuel,24", "1Ki_1.html,列王记上,1 Kings,22",
			"2Ki_1.html,列王记下,2 Kings,25", "1Ch_1.html,历代记上,1 Chronicles,29",
			"2Ch_1.html,历代记下,2 Chronicles,36", "Ezr_1.html,以斯拉记,Ezra,10",
			"Neh_1.html,尼希米记,Nehemiah,13", "Est_1.html,以斯帖记,Esther,10",
			"Job_1.html,约伯记,Job,42", "Psm_1.html,诗篇,Psalms,150",
			"Pro_1.html,箴言,Proverbs,31", "Ecc_1.html,传道书,Ecclesiastes,12",
			"Son_1.html,雅歌,Song of Songs,8", "Isa_1.html,以赛亚书,Isaiah,66",
			"Jer_1.html,耶利米书,Jeremiah,52", "Lam_1.html,耶利米哀歌,Lamentations,5",
			"Eze_1.html,以西结书,Ezekiel,48", "Dan_1.html,但以理书,Daniel,12",
			"Hos_1.html,何西阿书,Hosea,14", "Joe_1.html,约珥书,Joel,3",
			"Amo_1.html,阿摩司书,Amos,9", "Oba_1.html,俄巴底亚书,Obadiah,1",
			"Jon_1.html,约拿书,Jonah,4", "Mic_1.html,弥迦书,Micah,7",
			"Nah_1.html,那鸿书,Nahum,3", "Hab_1.html,哈巴谷书,Habakkuk,3",
			"Zep_1.html,西番雅书,Zephaniah,3", "Hag_1.html,哈该书,Haggai,2",
			"Zec_1.html,撒迦利亚书,Zechariah,14", "Mal_1.html,玛拉基书,Malachi,4",
			"Mat_1.html,马太福音,Matthew,28", "Mak_1.html,马可福音,Mark,16",
			"Luk_1.html,路加福音,Luke,24", "Jhn_1.html,约翰福音,John,21",
			"Act_1.html,使徒行传,Acts,28", "Rom_1.html,罗马书,Romans,16",
			"1Co_1.html,哥林多前书,1 Corinthians,16",
			"2Co_1.html,哥林多后书,2 Corinthians,13", "Gal_1.html,加拉太书,Galatians,6",
			"Eph_1.html,以弗所书,Ephesians,6", "Phl_1.html,腓立比书,Philippians,4",
			"Col_1.html,歌罗西书,Colossians,4",
			"1Ts_1.html,帖撒罗尼迦前书,1 Thessalonians,5",
			"2Ts_1.html,帖撒罗尼迦后书,2 Thessalonians,3",
			"1Ti_1.html,提摩太前书,1 Timothy,6", "2Ti_1.html,提摩太后书,2 Timothy,4",
			"Tit_1.html,提多书,Titus,3", "Phm_1.html,腓利门书,Philemon,1",
			"Heb_1.html,希伯来书,Hebrews,13", "Jas_1.html,雅各书,James,5",
			"1Pe_1.html,彼得前书,1 Peter,5", "2Pe_1.html,彼得后书,2 Peter,3",
			"1Jn_1.html,约翰一书,1 John,5", "2Jn_1.html,约翰二书,2 John,1",
			"3Jn_1.html,约翰三书,3 John,1", "Jud_1.html,犹大书,Jude,1",
			"Rev_1.html,启示录,Revelation,22" };
	
	String[] zhang3 = new String[] {
			"Judges 5,07 士师记 - Judges/Jug_5.html",
			"Judges 6,07 士师记 - Judges/Jug_6.html",
			"Judges 7,07 士师记 - Judges/Jug_7.html",
			"Judges 8,07 士师记 - Judges/Jug_8.html",
			"Judges 9,07 士师记 - Judges/Jug_9.html",
			"Judges 10,07 士师记 - Judges/Jug_10.html",
			"Judges 11,07 士师记 - Judges/Jug_11.html",
			"Judges 12,07 士师记 - Judges/Jug_12.html",
			"Judges 13,07 士师记 - Judges/Jug_13.html",
			"Judges 14,07 士师记 - Judges/Jug_14.html",
			"Judges 15,07 士师记 - Judges/Jug_15.html",
			"Judges 16,07 士师记 - Judges/Jug_16.html",
			"Judges 17,07 士师记 - Judges/Jug_17.html",
			"Judges 18,07 士师记 - Judges/Jug_18.html",
			"Judges 19,07 士师记 - Judges/Jug_19.html",
			"Judges 20,07 士师记 - Judges/Jug_20.html",
			"Judges 21,07 士师记 - Judges/Jug_21.html",
			"1 Kings 6,11 列王记上 - 1 Kings/1Ki_6.html",
			"1 Kings 7,11 列王记上 - 1 Kings/1Ki_7.html",
			"1 Kings 8,11 列王记上 - 1 Kings/1Ki_8.html",
			"1 Kings 9,11 列王记上 - 1 Kings/1Ki_9.html",
			"1 Kings 10,11 列王记上 - 1 Kings/1Ki_10.html",
			"1 Kings 11,11 列王记上 - 1 Kings/1Ki_11.html",
			"1 Kings 12,11 列王记上 - 1 Kings/1Ki_12.html",
			"1 Kings 13,11 列王记上 - 1 Kings/1Ki_13.html",
			"1 Kings 14,11 列王记上 - 1 Kings/1Ki_14.html",
			"1 Kings 15,11 列王记上 - 1 Kings/1Ki_15.html",
			"1 Kings 16,11 列王记上 - 1 Kings/1Ki_16.html",
			"1 Kings 17,11 列王记上 - 1 Kings/1Ki_17.html",
			"1 Kings 18,11 列王记上 - 1 Kings/1Ki_18.html",
			"1 Kings 19,11 列王记上 - 1 Kings/1Ki_19.html",
			"1 Kings 20,11 列王记上 - 1 Kings/1Ki_20.html",
			"1 Kings 21,11 列王记上 - 1 Kings/1Ki_21.html",
			"1 Kings 22,11 列王记上 - 1 Kings/1Ki_22.html",
			"Psalms 51,19 诗篇 - Psalms/Psm_51.html",
			"Psalms 52,19 诗篇 - Psalms/Psm_52.html",
			"Psalms 53,19 诗篇 - Psalms/Psm_53.html",
			"Psalms 54,19 诗篇 - Psalms/Psm_54.html",
			"Psalms 55,19 诗篇 - Psalms/Psm_55.html",
			"Psalms 56,19 诗篇 - Psalms/Psm_56.html",
			"Psalms 57,19 诗篇 - Psalms/Psm_57.html",
			"Psalms 58,19 诗篇 - Psalms/Psm_58.html",
			"Psalms 59,19 诗篇 - Psalms/Psm_59.html",
			"Psalms 60,19 诗篇 - Psalms/Psm_60.html",
			"Psalms 61,19 诗篇 - Psalms/Psm_61.html",
			"Psalms 62,19 诗篇 - Psalms/Psm_62.html",
			"Psalms 63,19 诗篇 - Psalms/Psm_63.html",
			"Psalms 64,19 诗篇 - Psalms/Psm_64.html",
			"Psalms 65,19 诗篇 - Psalms/Psm_65.html",
			"Psalms 66,19 诗篇 - Psalms/Psm_66.html",
			"Psalms 67,19 诗篇 - Psalms/Psm_67.html",
			"Psalms 68,19 诗篇 - Psalms/Psm_68.html",
			"Psalms 69,19 诗篇 - Psalms/Psm_69.html",
			"Psalms 70,19 诗篇 - Psalms/Psm_70.html",
			"Psalms 71,19 诗篇 - Psalms/Psm_71.html",
			"Psalms 72,19 诗篇 - Psalms/Psm_72.html",
			"Psalms 73,19 诗篇 - Psalms/Psm_73.html",
			"Psalms 74,19 诗篇 - Psalms/Psm_74.html",
			"Psalms 75,19 诗篇 - Psalms/Psm_75.html",
			"Psalms 76,19 诗篇 - Psalms/Psm_76.html",
			"Psalms 77,19 诗篇 - Psalms/Psm_77.html",
			"Psalms 78,19 诗篇 - Psalms/Psm_78.html",
			"Psalms 79,19 诗篇 - Psalms/Psm_79.html",
			"Psalms 80,19 诗篇 - Psalms/Psm_80.html",
			"Psalms 81,19 诗篇 - Psalms/Psm_81.html",
			"Psalms 82,19 诗篇 - Psalms/Psm_82.html",
			"Psalms 83,19 诗篇 - Psalms/Psm_83.html",
			"Psalms 84,19 诗篇 - Psalms/Psm_84.html",
			"Psalms 85,19 诗篇 - Psalms/Psm_85.html",
			"Psalms 86,19 诗篇 - Psalms/Psm_86.html",
			"Psalms 87,19 诗篇 - Psalms/Psm_87.html",
			"Psalms 88,19 诗篇 - Psalms/Psm_88.html",
			"Psalms 89,19 诗篇 - Psalms/Psm_89.html",
			"Psalms 90,19 诗篇 - Psalms/Psm_90.html",
			"Psalms 91,19 诗篇 - Psalms/Psm_91.html",
			"Psalms 92,19 诗篇 - Psalms/Psm_92.html",
			"Psalms 93,19 诗篇 - Psalms/Psm_93.html",
			"Psalms 94,19 诗篇 - Psalms/Psm_94.html",
			"Psalms 95,19 诗篇 - Psalms/Psm_95.html",
			"Psalms 96,19 诗篇 - Psalms/Psm_96.html",
			"Psalms 97,19 诗篇 - Psalms/Psm_97.html",
			"Psalms 98,19 诗篇 - Psalms/Psm_98.html",
			"Psalms 99,19 诗篇 - Psalms/Psm_99.html",
			"Psalms 100,19 诗篇 - Psalms/Psm_100.html",
			"Psalms 101,19 诗篇 - Psalms/Psm_101.html",
			"Psalms 102,19 诗篇 - Psalms/Psm_102.html",
			"Psalms 103,19 诗篇 - Psalms/Psm_103.html",
			"Psalms 104,19 诗篇 - Psalms/Psm_104.html",
			"Psalms 105,19 诗篇 - Psalms/Psm_105.html",
			"Psalms 106,19 诗篇 - Psalms/Psm_106.html",
			"Psalms 107,19 诗篇 - Psalms/Psm_107.html",
			"Psalms 108,19 诗篇 - Psalms/Psm_108.html",
			"Psalms 109,19 诗篇 - Psalms/Psm_109.html",
			"Psalms 110,19 诗篇 - Psalms/Psm_110.html",
			"Psalms 111,19 诗篇 - Psalms/Psm_111.html",
			"Psalms 112,19 诗篇 - Psalms/Psm_112.html",
			"Psalms 113,19 诗篇 - Psalms/Psm_113.html",
			"Psalms 114,19 诗篇 - Psalms/Psm_114.html",
			"Psalms 115,19 诗篇 - Psalms/Psm_115.html",
			"Psalms 116,19 诗篇 - Psalms/Psm_116.html",
			"Psalms 117,19 诗篇 - Psalms/Psm_117.html",
			"Psalms 118,19 诗篇 - Psalms/Psm_118.html",
			"Psalms 119,19 诗篇 - Psalms/Psm_119.html",
			"Psalms 120,19 诗篇 - Psalms/Psm_120.html",
			"Psalms 121,19 诗篇 - Psalms/Psm_121.html",
			"Psalms 122,19 诗篇 - Psalms/Psm_122.html",
			"Psalms 123,19 诗篇 - Psalms/Psm_123.html",
			"Psalms 124,19 诗篇 - Psalms/Psm_124.html",
			"Psalms 125,19 诗篇 - Psalms/Psm_125.html",
			"Psalms 126,19 诗篇 - Psalms/Psm_126.html",
			"Psalms 127,19 诗篇 - Psalms/Psm_127.html",
			"Psalms 128,19 诗篇 - Psalms/Psm_128.html",
			"Psalms 129,19 诗篇 - Psalms/Psm_129.html",
			"Psalms 130,19 诗篇 - Psalms/Psm_130.html",
			"Psalms 131,19 诗篇 - Psalms/Psm_131.html",
			"Psalms 132,19 诗篇 - Psalms/Psm_132.html",
			"Psalms 133,19 诗篇 - Psalms/Psm_133.html",
			"Psalms 134,19 诗篇 - Psalms/Psm_134.html",
			"Psalms 135,19 诗篇 - Psalms/Psm_135.html",
			"Psalms 136,19 诗篇 - Psalms/Psm_136.html",
			"Psalms 137,19 诗篇 - Psalms/Psm_137.html",
			"Psalms 138,19 诗篇 - Psalms/Psm_138.html",
			"Psalms 139,19 诗篇 - Psalms/Psm_139.html",
			"Psalms 140,19 诗篇 - Psalms/Psm_140.html",
			"Psalms 141,19 诗篇 - Psalms/Psm_141.html",
			"Psalms 142,19 诗篇 - Psalms/Psm_142.html",
			"Psalms 143,19 诗篇 - Psalms/Psm_143.html",
			"Psalms 144,19 诗篇 - Psalms/Psm_144.html",
			"Psalms 145,19 诗篇 - Psalms/Psm_145.html",
			"Psalms 146,19 诗篇 - Psalms/Psm_146.html",
			"Psalms 147,19 诗篇 - Psalms/Psm_147.html",
			"Psalms 148,19 诗篇 - Psalms/Psm_148.html",
			"Psalms 149,19 诗篇 - Psalms/Psm_149.html",
			"Psalms 150,19 诗篇 - Psalms/Psm_150.html",
			"Isaiah 51,23 以赛亚书 - Isaiah/Isa_51.html",
			"Isaiah 52,23 以赛亚书 - Isaiah/Isa_52.html",
			"Isaiah 53,23 以赛亚书 - Isaiah/Isa_53.html",
			"Isaiah 54,23 以赛亚书 - Isaiah/Isa_54.html",
			"Isaiah 55,23 以赛亚书 - Isaiah/Isa_55.html",
			"Isaiah 56,23 以赛亚书 - Isaiah/Isa_56.html",
			"Isaiah 57,23 以赛亚书 - Isaiah/Isa_57.html",
			"Isaiah 58,23 以赛亚书 - Isaiah/Isa_58.html",
			"Isaiah 59,23 以赛亚书 - Isaiah/Isa_59.html",
			"Isaiah 60,23 以赛亚书 - Isaiah/Isa_60.html",
			"Isaiah 61,23 以赛亚书 - Isaiah/Isa_61.html",
			"Isaiah 62,23 以赛亚书 - Isaiah/Isa_62.html",
			"Isaiah 63,23 以赛亚书 - Isaiah/Isa_63.html",
			"Isaiah 64,23 以赛亚书 - Isaiah/Isa_64.html",
			"Isaiah 65,23 以赛亚书 - Isaiah/Isa_65.html",
			"Isaiah 66,23 以赛亚书 - Isaiah/Isa_66.html",
			"Jeremiah 51,24 耶利米书 - Jeremiah/Jer_51.html",
			"Jeremiah 52,24 耶利米书 - Jeremiah/Jer_52.html"
	};

	//"1 Kings 7,1Ki",
	public void printSomeInfo()
			throws SQLException {
		for (String s : re_zhang) {
			String[] arr = s.split(",");
			String shr = arr[1];
			
			String[] arr2 = arr[0].split(" ");
			String zhang_sort = arr2[arr2.length - 1];
			String pian_enname = arr[0].substring(0, arr[0].length() - zhang_sort.length() - 1);
			Map<String, Object> item = new TestDAO().getItemByRefId(pian_enname);
			String pian_cnname = (String)item.get("name");
			String pian_sort = ((Integer) item.get("sort_order")).toString();
			if (pian_sort.length() == 1) {
				pian_sort = "0" + pian_sort;
			}
			String parturl = pian_sort + " " + pian_cnname + " - " + pian_enname + "/" + shr + "_" + zhang_sort + ".html";
			String zhang_ref_id = arr[0];
			System.out.println("\"" + zhang_ref_id + "," + parturl + "\",");
		}
	} 
	
	public String getOnePageHtml(String url) throws IOException {
		String r = new WebCatcher().getOnePageHtml(url, encoding);
		return r;
	}

	public void createIndexByArr() throws Exception {
		createIndexByArr(null, -1);
	}

	public void createTOCOfBible() throws Exception {
		try {
			// 马太福音-Matthew
			BibleCreator bc = new BibleCreator();
			int old_parent[] = bc.createOldTestament();
			int new_parent[] = bc.createNewTestament();

			{
				int matindex = 100000;
				for (int index = 0; index < indexs.length; index++) {
					String link = indexs[index];
					String[] arr = link.split(",");
					String cnname = arr[1];
					String enname = arr[2];
					String ref_id = enname;
					int parent[] = old_parent;
					if ("Matthew".equals(enname)) {
						matindex = index;
					}
					if (index >= matindex) {
						parent = new_parent;
					}
					bc.createFolder(parent[0], cnname, enname, ref_id);
				}
			}

			{
				int matindex = 100000;
				for (int index = 0; index < indexs.length; index++) {
					String link = indexs[index];
					String[] arr = link.split(",");
					String cnname = arr[1];
					String enname = arr[2];
					int count = Integer.parseInt(arr[3]);
					String ref_id = enname;
					int parent[] = old_parent;
					if ("Matthew".equals(enname)) {
						matindex = index;
					}
					if (index >= matindex) {
						parent = new_parent;
					}
					int pianinfo[] = bc.createFolder(parent[0], cnname, enname,
							ref_id);
					for (int i = 0; i < count; i++) {
						int sort_order = i + 1;
						String zhang_ref_id = enname + " " + sort_order;
						String zhang_enname = "Chapter " + sort_order;
						String zhang_cnname = "第 " + sort_order + " 章";
						bc.createFolder(pianinfo[0], zhang_cnname,
								zhang_enname, zhang_ref_id);

					}
				}
			}

		} catch (Exception e) {
			System.out.println("createIndex First error!" + e.getMessage());
			throw e;
		}
	}

	public void createIndexByArr(String beginPianRefId, int zhangSortId)
			throws Exception {
		try {

			// 马太福音-Matthew
			BibleCreator bc = new BibleCreator();
			int old_parent[] = bc.createOldTestament();
			int new_parent[] = bc.createNewTestament();

			int beginIndex = 0;
			if (beginPianRefId != null && beginPianRefId.length() > 0) {
				for (int i = 0; i < indexs.length; i++) {
					String xx = indexs[i];
					String[] arr = xx.split(",");
					String ref_id = arr[2];
					if (beginPianRefId.equals(ref_id)) {
						beginIndex = i;
						break;
					}
				}
			}

			int matindex = 100000;

			for (int index = 0; index < indexs.length; index++) {
				if (index < beginIndex) {
					continue;
				}
				String link = indexs[index];
				String[] arr = link.split(",");
				String parturl = arr[0];
				String cnname = arr[1];
				String enname = arr[2];
				int count = Integer.parseInt(arr[3]);
				String ref_id = enname;
				int parent[] = old_parent;
				if ("Matthew".equals(enname)) {
					matindex = index;
				}
				if (index >= matindex) {
					parent = new_parent;
				}
				int pianinfo[] = bc.createFolder(parent[0], cnname, enname,
						ref_id);
				String preurl = parturl.split("_")[0];
				for (int i = 0; i < count; i++) {
					if (index == beginIndex) {
						if (i < (zhangSortId - 1)) {
							continue;
						}
					}
					int sort_order = i + 1;
					String curl = preurl + "_" + sort_order + ".html";
					createZhang(curl, pianinfo, enname, sort_order);
				}
			}
		} catch (Exception e) {
			System.out.println("createIndex error!" + e.getMessage());
			throw e;
		}
	}

	public void createZhang(String parturl, int[] pianinfo,
			String parent_enname, int sort_order) throws Exception {
		try {

			BibleCreator bc = new BibleCreator();
			String zhang_ref_id = parent_enname + " " + sort_order;
			String zhang_enname = "Chapter " + sort_order;
			String zhang_cnname = "第 " + sort_order + " 章";
			int[] zhanginfo = bc.createFolder(pianinfo[0], zhang_cnname,
					zhang_enname, zhang_ref_id);

			List<String[]> list = getZhangInfo(parturl);

			for (String[] arr : list) {
				String ju_ref_id = parent_enname + " " + sort_order + ":"
						+ arr[1];
				String ju_enname = sort_order + ":" + arr[1];
				String ju_cnname = sort_order + ":" + arr[1];
				String ju_cncontent = arr[2];
				String ju_encontent = arr[3];
				bc.createArticle(zhanginfo[0], ju_enname, ju_cnname,
						ju_cncontent, ju_encontent, ju_ref_id);
			}

			System.out.println(zhang_ref_id);

		} catch (Exception e) {
			System.out.println("createZhang error!" + parturl);
			throw e;
		}
	}

	public void checkZhangExist2() throws Exception {
		// "Judges 5,Jug",
		for (String zhaninfo : re_zhang) {
			String[] arr = zhaninfo.split(",");
			String zhang_ref_id = arr[0];
			String preurl = arr[1];
			String[] arr2 = zhang_ref_id.split(" ");
			int sort_order = Integer.parseInt(arr2[arr2.length - 1]);
			String parturl = preurl + "_" + sort_order + ".html";
			// System.out.println(zhang_ref_id + "\t" + parturl);
			// createZhang2(parturl, zhang_ref_id);
			List<String[]> list = getZhangInfo(parturl);

			if (list == null || list.size() <= 0) {
				System.out.println(zhang_ref_id + "\t" + parturl
						+ "\t not found!");
			}
		}
	}

	public void createZhang2() throws Exception {
		// "Judges 5,Jug",
		for (String zhaninfo : re_zhang) {
			String[] arr = zhaninfo.split(",");
			String zhang_ref_id = arr[0];
			String preurl = arr[1];
			String[] arr2 = zhang_ref_id.split(" ");
			int sort_order = Integer.parseInt(arr2[arr2.length - 1]);
			String parturl = preurl + "_" + sort_order + ".html";
			System.out.println(zhang_ref_id + "\t" + parturl);
			createZhang2(parturl, zhang_ref_id);
		}
	}
	

	private void createZhang2(String parturl, String zhang_ref_id)
			throws Exception {
		try {
			BibleCreator bc = new BibleCreator();
			TestDAO dao = new TestDAO();
			Map<String, Object> zhang = dao.getItemByRefId(zhang_ref_id);
			int zhang_sort_id = (Integer) zhang.get("sort_order");
			int zhang_tree_id = (Integer) zhang.get("tree_id");
			List<String[]> list = getZhangInfo(parturl);

			if (list == null || list.size() < 0) {
				System.out.println(zhang_ref_id + "\t" + parturl
						+ "\t not found!");
			} else {
				for (String[] arr : list) {
					String ju_ref_id = zhang_ref_id + ":" + arr[1];
					String ju_enname = zhang_sort_id + ":" + arr[1];
					String ju_cnname = zhang_sort_id + ":" + arr[1];
					String ju_cncontent = arr[2];
					String ju_encontent = arr[3];
					bc.createArticle(zhang_tree_id, ju_enname, ju_cnname,
							ju_cncontent, ju_encontent, ju_ref_id);
				}

				System.out.println(zhang_ref_id);
			}

		} catch (Exception e) {
			System.out.println("createZhang error!" + parturl);
			throw e;
		}
	}
	

	public void createZhang3() throws Exception {
		//Psalms 114,19 诗篇 - Psalms/Psm_114.html
		for (String zhaninfo : zhang3) {
			String[] arr = zhaninfo.split(",");
			String zhang_ref_id = arr[0];
			String parturl = arr[1].replace(" ", "%20");
			createZhang2(parturl, zhang_ref_id);
		}
	}

	// <td width=50>1:1</td><td>起初神创造天地。<br>In the beginning God created the
	// heaven and the earth.</td>
	public List<String[]> getZhangInfo(String parturl) throws Exception {
		try {
			String html = getOnePageHtml(rooturl2 + parturl);

			List<String[]> list = new ArrayList<String[]>();
			String regex = "<td[^>]*width=50>([0-9]+):([0-9]+)</td><td>([^>]*)<br>([^>]*)</td>";
			Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
			Matcher ma = pa.matcher(html);
			while (ma.find()) {
				String s0 = ma.group();
				String s1 = ma.group(1);
				String s2 = ma.group(2);
				String s3 = ma.group(3);
				String s4 = ma.group(4);
				String[] arr = new String[] { s1, s2, s3, s4 };
				list.add(arr);
			}
			System.out.println("get from " + parturl);
			return list;

		} catch (Exception e) {
			System.out
					.println("getZhangInfo error!" + parturl + e.getMessage());
			throw e;
		}
	}

	public void createIndexByWeb() throws Exception {
		try {
			// 马太福音-Matthew
			BibleCreator bc = new BibleCreator();
			int new_parent[] = bc.createNewTestament();
			int old_parent[] = bc.createOldTestament();
			String html = getOnePageHtml(rooturl + "index.htm");
			List<String> links = getLinks(html);
			int matindex = 100000;
			int index = 0;
			for (String link : links) {
				String[] arr = parseIndexLink(link);
				String parturl = arr[0];
				String cnname = arr[1];
				String enname = arr[2];
				String ref_id = enname;
				int parent[] = old_parent;
				if ("Matthew".equals(enname)) {
					matindex = index;
				}
				if (index >= matindex) {
					parent = new_parent;
				}
				// bc.createFolder(parent[0], cnname, enname, ref_id);

				int count = createFirstZhang(parturl);
				System.out.println(parturl + "\t" + cnname + "\t" + enname
						+ "\t" + count);

				index++;
			}
		} catch (Exception e) {
			System.out.println("createIndex error!" + e.getMessage());
			throw e;
		}
	}

	// 本篇共有 50 章
	public int createFirstZhang(String parturl) throws Exception {
		try {
			String url = rooturl + parturl;
			String html = getOnePageHtml(url);

			int count = 0;

			String regex = "本篇共有 ([0-9]+) 章";
			Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
			Matcher matcher = pattern.matcher(html);
			if (matcher.find()) {
				String c = matcher.group(1).trim();
				count = Integer.parseInt(c);
			}

			return count;

		} catch (Exception e) {
			System.out.println("createOneIndex error!" + parturl);
			throw e;
		}
	}

	// Old Testament
	// New Testament

	// <a href="Gen_1.html" tppabs="Gen_1.html">创世记-Genesis</a>
	// <a href="1Ch_1.html" tppabs="1.html">历代记上-1 Chronicles</a>
	// <a href="2Ch_1.html" tppabs="2Ch_1.html">历代记下-2 Chronicles</a>
	private String[] parseIndexLink(String link) {
		String url = "";
		String cn = "";
		String en = "";

		String regex = "<a[^>]*href=(\"([^\"]*)\")[^>]*>(.*?)</a>";
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(link);
		if (matcher.find()) {
			url = matcher.group(2);
			String name = matcher.group(3).trim();
			String[] sarr = name.split("-");
			String cnname = sarr[0].trim();
			String enname = sarr[1].trim();
			// if (enname.startsWith("1") || enname.startsWith("2")) {
			// String seq = "1";
			// if (enname.startsWith("2")) {
			// seq = "2";
			// }
			// enname = enname.substring(1).trim() + "-" + seq;
			// cnname = cnname + "-" + seq;
			// }
			cn = cnname;
			en = enname;
		}

		String[] arr = new String[] { url, cn, en };
		return arr;
	}

	private List<String> getLinks(String html) {
		List<String> list = new ArrayList<String>();
		String regex = "<a[^>]*href=(\"([^\"]*)\"|/'([^/']*)/'|([^/s>]*))[^>]*>(.*?)</a>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(html);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}
}
