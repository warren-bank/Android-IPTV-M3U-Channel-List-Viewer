#### [M3U Viewer](https://github.com/warren-bank/Android-IPTV-M3U-Channel-List-Viewer)

Android app that reads an IPTV M3U file, and displays a list of channels.

#### Features:

* IPTV M3U file can be read from either a network URL or the local filesystem
* list of channels can be sorted and filtered
* clicking on any channel starts an Intent to watch its stream in an external video player

#### Settings:

1. Default M3U Playlist URL
   - Initial value in "Open M3U Playlist URL" dialog.
2. Channel URL Template
   - When any channel URL in the M3U does not begin with "http", parse it as a comma-separated list of values for substitution into this URL template.
   - Example:
     * Channel URL Template: `http://kytv.xyz:80/MY-USERNAME/MY-PASSWORD/%1$s.ts`
     * M3U file
       ```xml
       #EXTM3U
       #EXTINF:-1,CHANNEL 1
       11111
       #EXTINF:-1,CHANNEL 2
       22222
       #EXTINF:-1,CHANNEL 3
       https://example.com/33333.ts
       ```
     * Resolved Channel URLs
       - CHANNEL 1 = `http://kytv.xyz:80/MY-USERNAME/MY-PASSWORD/11111.ts`
       - CHANNEL 2 = `http://kytv.xyz:80/MY-USERNAME/MY-PASSWORD/22222.ts`
       - CHANNEL 3 = `https://example.com/33333.ts`
   - Java References:
     * [`String.format(template, values)`](https://developer.android.com/reference/java/lang/String#format(java.lang.String,%20java.lang.Object[]))
     * [template syntax](https://developer.android.com/reference/java/util/Formatter.html#syntax)

#### Intent filters:

Enables the automatic updating of channels from an externally bookmarked URL.<br>
The `data` URL must return a server response that contains a valid IPTV M3U file.

Supported Intents:

1. action = `android.intent.action.VIEW`
   * (optional) package = `com.github.warren_bank.m3u_viewer`
   * (optional) class   = `com.github.warren_bank.m3u_viewer.ChannelsActivity`
   * data = `<any URL>`
   * type = any of:
     ```json
     [
       "application/vnd.apple.mpegurl",
       "application/mpegurl",
       "audio/mpegurl",
       "video/mpegurl",
       "application/x-mpegurl",
       "audio/x-mpegurl",
       "video/x-mpegurl",
       "application/x-mpegURL",
       "audio/x-mpegURL",
       "video/x-mpegURL"
     ]
     ```
2. action = `android.intent.action.VIEW`
   * (optional) package = `com.github.warren_bank.m3u_viewer`
   * (optional) class   = `com.github.warren_bank.m3u_viewer.ChannelsActivity`
   * data = `<URL that ends with a .m3u or .M3U file extension>`

#### Legal:

* copyright: [Warren Bank](https://github.com/warren-bank)
* license: [GPL-2.0](https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt)
