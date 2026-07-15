package com.github.warren_bank.m3u_viewer.parsers;

import com.github.warren_bank.m3u_viewer.models.ChannelListItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

public class M3uParser {

    public static void writeFile(String filename, InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] buf;
            int length;

            filename += "\n";
            buf = filename.getBytes("UTF-8");
            length = buf.length;
            outputStream.write(buf, 0, length);

            buf = new byte[8192];
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }

            inputStream.close();
            outputStream.close();
        }
        catch(Exception e) {}
    }

    public static ArrayList<ChannelListItem> readFile(InputStream inputStream) {
        ArrayList<ChannelListItem> channels = new ArrayList<ChannelListItem>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String currentTitle = "";
            int nonce = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (nonce == 0) {
                    // M3U filename is prepended to 1st line of file content
                    channels.add(new ChannelListItem(line, null, nonce));
                    nonce += 1;
                } else if (line.startsWith("#EXTINF:")) {
                    // Extract title after the comma in #EXTINF:-1,Channel Title
                    int commaIndex = line.indexOf(",");
                    if (commaIndex != -1) {
                        currentTitle = line.substring(commaIndex + 1);
                    }
                } else if (!line.isEmpty() && !line.startsWith("#")) {
                    // It's a URL
                    channels.add(new ChannelListItem(currentTitle, line, nonce));
                    currentTitle = ""; // Reset
                    nonce += 1;
                }
            }
            reader.close();
        } catch (Exception e) {}
        return channels;
    }
}
