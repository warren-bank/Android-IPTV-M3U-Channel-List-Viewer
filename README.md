#### [M3U Viewer](https://github.com/warren-bank/Android-IPTV-M3U-Channel-List-Viewer)

Android app that reads an IPTV M3U file, and displays a list of channels.

#### Features:

* IPTV M3U file can be read from either a network URL or the local filesystem
* list of channels can be sorted and filtered
* clicking on any channel starts an Intent to watch its stream in an external video player

#### Intent filters:

To enable the automatic updating of channels from an externally bookmarked URL,<br>
which returns a server response containing a valid IPTV M3U file.

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
