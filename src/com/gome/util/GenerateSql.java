package com.gome.util;

public class GenerateSql {
	
	private static int times;
	private static final String TABSPACES = "\t\n";
	
	public static void gen(int month) {
		StringBuffer sqlbBuffer = new StringBuffer();
		String select = "select name, " + TABSPACES;
		String condition =	"";
		String from = "from ( " + TABSPACES 
					+ "    select name,clock_date,min(clock_time) as mintime,max(clock_time) as maxtime " + TABSPACES
					+ "    from excel_tab " + TABSPACES
					+ "    group by name,CLOCK_DATE " + TABSPACES
					+ ") a group by name; " + TABSPACES;
		
		if(String.valueOf(month).matches("1|3|5|7|8|10|12")) {
			times = 31;
		} else if(String.valueOf(month).equals("2")) {
			times = 28;
		} else {
			times = 30;
		}
		//System.out.println("待生成SQL月份："+month+", 次数："+times);
		
		for(int j = 1; j <= times; j ++) {
			String m = String.valueOf(month).length() == 1 ? "0" + month : month + "";
			String t = String.valueOf(j).length() == 1 ? "0" + j : j + "";
			condition += " max(case clock_date when '2018-" + m + "-"+t+"' then concat(case when mintime <= '12:00:00' then mintime else '未打卡' end, '-', case when maxtime > '12:00:00' then maxtime else '未打卡' end) else '' end) as '2018年"+month+"月"+j+"日'";
			if(j != times) {
				condition += ",";
			}
			condition += TABSPACES;
		}
		sqlbBuffer.append(select).append(condition).append(from);
		System.out.println(sqlbBuffer);
	}
	
	public static void main(String[] args) {
		gen(1);
		gen(2);
		gen(3);
		gen(4);
	}
}
