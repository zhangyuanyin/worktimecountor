package com.gome.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.gome.common.ConnectionFactory;

public class ReadFile {

	public static void read(String filename) {
		Connection connection = null;
		PreparedStatement ps = null;
		BufferedReader br = null;
		String sqlCommon = "insert into excel_tab values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		File file = new File(filename);
		if(!file.exists()) return;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			connection = ConnectionFactory.getConnnection();
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(" ");
				ps = connection.prepareStatement(sqlCommon);
				ps.setString(1, values[0]);
				ps.setString(2, values[1]);
				ps.setString(3, values[2]);
				ps.setString(4, values[3]);
				ps.setString(5, values[4]);
				ps.setString(6, values[5]);
				ps.setString(7, values[6]);
				ps.setString(8, values[7]);
				ps.setString(9, values[8]);
				ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(connection, ps);
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
