/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jums;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kotaroh
 */
public class JumsHelper {

    private final String TrainCheckURL = "TrainCheck";
    private final String TransferURL = "Transfer";
    private final String WeatherURL = "Weather";
    private final String ConvenienceStoreURL = "ConvenienceStore";
    private final String SettingURL = "Setting";

    Date date = new Date();
    SimpleDateFormat sdf1 = new SimpleDateFormat("a hh:mm:ss");

    public static JumsHelper getInstance() {
        return new JumsHelper();
    }

    //トップへのリンクを返却
    public String TrainCheck() {
        return "<div class=\"main\"><button type=\"button\" ><a href=\"" + TrainCheckURL + "\">運行情報</a></button></div>";
    }

    public String Transfer() {
        return "<div class=\"main\"><button type=\"button\" ><a href=\"" + TransferURL + "\">乗り換え</a></button></div>";
    }

    public String Weather() {
        return "<div class=\"main\"><button type=\"button\" ><a href=\"" + WeatherURL + "\">天気</a></button></div>";
    }

    public String ConvenienceStore() {
        return "<div class=\"main\"><button type=\"button\" ><a href=\"" + ConvenienceStoreURL + "\">駅周辺のコンビニ</a></button></div>";
    }

    public String Setting() {
        return "<div class=\"main\"><button type=\"button\" ><a href=\"" + SettingURL + "\">設定</a></button></div>";
    }

    public String ViewError(HttpServletRequest request) {
        String error = "";
        if (request.getAttribute("error") != null) {
            error = request.getAttribute("error").toString();
        }
        return "       <div align=\"center\">\n"
                + "            <h1>traINfo+</h1>\n"
                + "            " + sdf1.format(date) + "\n"
                + "            <table border=1  width=\"1100\" bgcolor=\"#dbd0e6\">\n"//白藤色
                + "                <tr>\n"
                + "                    <th width=\"150\">" + TrainCheck() + "</th>\n"
                + "                    <th width=\"150\">" + Transfer() + "</th>\n"
                + "                    <th width=\"150\">" + Weather() + "</th>\n"
                + "                    <th width=\"150\">" + ConvenienceStore() + "</th>\n"
                + "                    <th width=\"150\">" + Setting() + "</th>\n"
                + "                </tr>\n"
                + "            </table>\n"
                + error
                + "        </div>";
    }

    public String ViewTrainCheck(HttpServletRequest request) {
        String TrainCheck = "";
        //    if ("true".equals(request.getAttribute("tc"))) {
        TrainCheck = "遅延だ";
        //  }
        return "       <div align=\"center\">\n"
                + "            <h1>traINfo+</h1>\n"
                + "            " + sdf1.format(date) + "\n"
                + "            <table border=1  width=\"1100\" bgcolor=\"#dbd0e6\">\n"//白藤色
                + "                <tr>\n"
                + "                    <th width=\"150\"bgcolor=\"#a59aca\">" + TrainCheck() + "</th>\n"
                + "                    <th width=\"150\">" + Transfer() + "</th>\n"
                + "                    <th width=\"150\">" + Weather() + "</th>\n"
                + "                    <th width=\"150\">" + ConvenienceStore() + "</th>\n"
                + "                    <th width=\"150\">" + Setting() + "</th>\n"
                + "                </tr>\n"
                + "            </table><br>\n"
                + request.getAttribute("tc") + "<br>"
                + request.getAttribute("a")
                + "        </div>";
    }

    public String javascript() {
        String str = "    <script type=\"text/javascript\">\n"
                + "    <!--\n"
                + "if( navigator.geolocation )\n"
                + "{\n"
                + "	// 現在地を取得\n"
                + "	navigator.geolocation.getCurrentPosition(\n"
                + "    	// [第1引数] 取得に成功した場合の関数\n"
                + "		function( position )\n"
                + "		{\n"
                + "			// 取得したデータの整理\n"
                + "			var data = position.coords ;\n"
                + "			// データの整理\n"
                + "			var lat = data.latitude ;\n"
                + "			var lng = data.longitude ;\n"
                + "			// アラート表示\n"
                + "			alert( \"あなたの現在位置は、\\n[\" + lat + \",\" + lng + \"]\\nです。\" ) ;\n"
                + "			// HTMLへの書き出し\n"
                + "			document.getElementById( 'result' ).innerHTML = \n"
                + "                                '<% session.setAttribute(\"lat\",'+lat+'); %>'\n"
                + "                                + '</dd><dt><input type=\"hidden\" name=\"lng\" value=\"'+lng+'\"></dt><dd>'+lat+lng;\n"
                + "		},\n"
                + "		// [第2引数] 取得に失敗した場合の関数\n"
                + "		function( error )\n"
                + "		{\n"
                + "			// エラーコード(error.code)の番号\n"
                + "			// 0:UNKNOWN_ERROR				原因不明のエラー\n"
                + "			// 1:PERMISSION_DENIED			利用者が位置情報の取得を許可しなかった\n"
                + "			// 2:POSITION_UNAVAILABLE		電波状況などで位置情報が取得できなかった\n"
                + "			// 3:TIMEOUT					位置情報の取得に時間がかかり過ぎた…\n"
                + "\n"
                + "			// エラー番号に対応したメッセージ\n"
                + "			var errorInfo = [\n"
                + "				\"原因不明のエラーが発生しました…。\" ,\n"
                + "				\"位置情報の取得が許可されませんでした…。\" ,\n"
                + "				\"電波状況などで位置情報が取得できませんでした…。\" ,\n"
                + "				\"位置情報の取得に時間がかかり過ぎてタイムアウトしました…。\"\n"
                + "			] ;\n"
                + "\n"
                + "			// エラー番号\n"
                + "			var errorNo = error.code ;\n"
                + "\n"
                + "			// エラーメッセージ\n"
                + "			var errorMessage = \"[エラー番号: \" + errorNo + \"]\\n\" + errorInfo[ errorNo ] ;\n"
                + "\n"
                + "			// アラート表示\n"
                + "			alert( errorMessage ) ;\n"
                + "\n"
                + "			// HTMLに書き出し\n"
                + "			document.getElementById(\"result\").innerHTML = errorMessage;\n"
                + "		} ,\n"
                + "		// [第3引数] オプション\n"
                + "		{\n"
                + "			\"enableHighAccuracy\": false,\n"
                + "			\"timeout\": 8000,\n"
                + "			\"maximumAge\": 2000,\n"
                + "		}\n"
                + "	) ;\n"
                + "}\n"
                + "// 対応していない場合\n"
                + "else\n"
                + "{\n"
                + "	// エラーメッセージ\n"
                + "	var errorMessage = \"お使いの端末は、GeoLacation APIに対応していません。\" ;\n"
                + "	// アラート表示\n"
                + "	alert( errorMessage ) ;\n"
                + "	// HTMLに書き出し\n"
                + "	document.getElementById( 'result' ).innerHTML = errorMessage ;\n"
                + "}\n"
                + "   -->\n"
                + "    </script>\n"
                + "    <dl id=\"result\"></dl>\n"
                + "";
        return str;
    }

}
