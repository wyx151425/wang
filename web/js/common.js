/**
 * Created by WangZhenqi on 2016/12/21.
 */
function getElem(a) {
    return document.getElementById(a)
}
function getElemValue(a) {
    return document.getElementById(a).value
}
function checkIsEmpty(a) {
    return null != a ? "" == document.getElementById(a).value.trim() ? !1 : !0 : void 0
}
function checkTextLength(a, b) {
    return null != a ? document.getElementById(a).value.trim().length > b ? !1 : !0 : void 0
}
function trim(a) {
    return a.replace(/(^\s*)|(\s*$)/g, "")
}
function ltrim(a) {
    return a.replace(/(^\s*)/g, "")
}
function rtrim(a) {
    return a.replace(/(\s*$)/g, "")
}
function checkIsDecimal(a, b) {
    if (a = new String(a), null == a.match(/^\d*\.?\d{0,2}$/))return !1;
    var c = a.split(".");
    if (1 == c.length) {
        if (c[0].trim().length < 1)return !1
    } else {
        if (2 != c.length)return !1;
        if (c[0].trim().length < 1 || c[1].length != b)return !1
    }
    return !0
}
function CheckIsMoney(a) {
    if (0 == a)return !1;
    var b = new RegExp(/^\d*\.?\d{0,2}$/);
    return b.test(a) ? Number(a) < .01 ? !1 : !0 : !1
}
function checkIsNumberic(a) {
    return a = new String(a), null == a.match(/^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$/g) ? !1 : !0
}
function checkIsCharAndNum(a) {
    var b = /^([A-Z]|[^u4E00-u9FA5]|[a-z]|[\d])*$/g;
    return null == b.exec(a) ? !1 : !0
}
function checkNumber(a) {
    var b = a.trim(), c = /[^0-9]/g;
    return null == c.exec(b) ? !0 : !1
}
function checkPlusNumber(a) {
    var b = a.trim(), c = /^[1-9]\d*$/;
    return null == c.exec(b) ? !1 : !0
}
function checkLetter(a) {
    var b = a.trim(), c = /[^A-Za-z]/g;
    return null == c.exec(b) ? !0 : !1
}
function checkChinese(a) {
    var b = a.trim(), c = /[^\u4E00-\u9FA5]/g;
    return null == c.exec(b) ? !0 : !1
}
function IsURL(a) {
    var b = a.trim();
    r = !1;
    var c = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    return null == c.exec(b) ? r = !1 : r = !0, r
}
function checkEmail(a) {
    var b = !1, c = /^([a-zA-Z0-9_\-\.\+]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return b = null == c.exec(a) ? !1 : !0
}
function checkZipCode(a) {
    var b = !1, c = /^[0-9]{6}$/;
    return b = null == c.exec(a) ? !1 : !0
}
function checkMobile(a) {
    if ("" == a)return !1;
    var b = a;
    if ("" == a.trim())return !1;
    var c = !1, f = /^\d{11}$/, g = /^\d{12}$/;
    return 12 == b.length && (c = null == g.exec(b) ? !1 : !0), 11 == b.length && (c = null == f.exec(b) ? !1 : !0), c
}
function checkTelNum(a) {
    if ("" == a)return !1;
    var b = !1, c = /^[0-9]{7,8}$/, d = /^[0-9]{3,4}[-]{1}[0-9]{7,8}[-]{1}[0-9]{2,4}$/, e = /^[0-9]{3,4}[-]{1}[0-9]{7,8}$/, f = /^[0-9]{7,8}[-]{1}[0-9]{2,4}$/, g = /(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/, h = /^[48]00[-]{1}\d{3}[-]{1}\d{4}$/;
    return b = null == c.exec(a) && null == d.exec(a) && null == e.exec(a) && null == f.exec(a) && null == g.exec(a) && null == h.exec(a) ? !1 : !0
}
function checkIDNO(a) {
    var a, d, e, f, g, b = new Array("\u9a8c\u8bc1\u901a\u8fc7!", "\u8eab\u4efd\u8bc1\u53f7\u7801\u4f4d\u6570\u4e0d\u5bf9!", "\u8eab\u4efd\u8bc1\u53f7\u7801\u51fa\u751f\u65e5\u671f\u8d85\u51fa\u8303\u56f4\u6216\u542b\u6709\u975e\u6cd5\u5b57\u7b26!", "\u8eab\u4efd\u8bc1\u53f7\u7801\u6821\u9a8c\u9519\u8bef!", "\u8eab\u4efd\u8bc1\u5730\u533a\u975e\u6cd5!"), c = {
        11: "\u5317\u4eac",
        12: "\u5929\u6d25",
        13: "\u6cb3\u5317",
        14: "\u5c71\u897f",
        15: "\u5185\u8499\u53e4",
        21: "\u8fbd\u5b81",
        22: "\u5409\u6797",
        23: "\u9ed1\u9f99\u6c5f",
        31: "\u4e0a\u6d77",
        32: "\u6c5f\u82cf",
        33: "\u6d59\u6c5f",
        34: "\u5b89\u5fbd",
        35: "\u798f\u5efa",
        36: "\u6c5f\u897f",
        37: "\u5c71\u4e1c",
        41: "\u6cb3\u5357",
        42: "\u6e56\u5317",
        43: "\u6e56\u5357",
        44: "\u5e7f\u4e1c",
        45: "\u5e7f\u897f",
        46: "\u6d77\u5357",
        50: "\u91cd\u5e86",
        51: "\u56db\u5ddd",
        52: "\u8d35\u5dde",
        53: "\u4e91\u5357",
        54: "\u897f\u85cf",
        61: "\u9655\u897f",
        62: "\u7518\u8083",
        63: "\u9752\u6d77",
        64: "\u5b81\u590f",
        65: "\u65b0\u7586",
        71: "\u53f0\u6e7e",
        81: "\u9999\u6e2f",
        82: "\u6fb3\u95e8",
        91: "\u56fd\u5916"
    }, h = new Array;
    if (h = a.split(""), null == c[parseInt(a.substr(0, 2))])return b[4];
    switch (a.length) {
        case 15:
            return (parseInt(a.substr(6, 2)) + 1900) % 4 == 0 || (parseInt(a.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(a.substr(6, 2)) + 1900) % 4 == 0 ? ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/ : ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/, ereg.test(a) ? b[0] : b[2];
        case 18:
            return parseInt(a.substr(6, 4)) % 4 == 0 || parseInt(a.substr(6, 4)) % 100 == 0 && parseInt(a.substr(6, 4)) % 4 == 0 ? ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/ : ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/, ereg.test(a) ? (f = 7 * (parseInt(h[0]) + parseInt(h[10])) + 9 * (parseInt(h[1]) + parseInt(h[11])) + 10 * (parseInt(h[2]) + parseInt(h[12])) + 5 * (parseInt(h[3]) + parseInt(h[13])) + 8 * (parseInt(h[4]) + parseInt(h[14])) + 4 * (parseInt(h[5]) + parseInt(h[15])) + 2 * (parseInt(h[6]) + parseInt(h[16])) + 1 * parseInt(h[7]) + 6 * parseInt(h[8]) + 3 * parseInt(h[9]), d = f % 11, g = "F", e = "10X98765432", g = e.substr(d, 1), g == h[17] ? b[0] : b[3]) : b[2];
        default:
            return b[1]
    }
}
function checkHtml(a) {
    var b = a, c = /[\<\>\<>\/>\<\/>]/g;
    return null == c.exec(b) ? !0 : !1
}
function getParasValue(a) {
    var b = window.location.search;
    if (-1 != b.indexOf(a)) {
        var c = b.indexOf(a) + a.length + 1, d = b.indexOf("&", c);
        return -1 == d ? b.substring(c) : b.substring(c, d)
    }
    return ""
}
function Encrypt(a, b) {
    if ("" == a)return "";
    if (a = escape(a), !b || "" == b)var b = "1234";
    if (b = escape(b), null == b || b.length <= 0)return alert("Please enter a password with which to encrypt the message."), null;
    for (var c = "", d = 0; d < b.length; d++)c += b.charCodeAt(d).toString();
    var e = Math.floor(c.length / 5), f = parseInt(c.charAt(e) + c.charAt(2 * e) + c.charAt(3 * e) + c.charAt(4 * e) + c.charAt(5 * e)), g = Math.ceil(b.length / 2), h = Math.pow(2, 31) - 1;
    if (2 > f)return alert("Algorithm cannot find a suitable hash. Please choose a different password. \nPossible considerations are to choose a more complex or longer password."), null;
    var i = Math.round(1e9 * Math.random()) % 1e8;
    for (c += i; c.length > 10;)c = (parseInt(c.substring(0, 10)) + parseInt(c.substring(10, c.length))).toString();
    c = (f * c + g) % h;
    for (var j = "", k = "", d = 0; d < a.length; d++)j = parseInt(a.charCodeAt(d) ^ Math.floor(c / h * 255)), k += 16 > j ? "0" + j.toString(16) : j.toString(16), c = (f * c + g) % h;
    for (i = i.toString(16); i.length < 8;)i = "0" + i;
    return k += i
}
function Decrypt(a, b) {
    if ("" == a)return "";
    if (!b || "" == b)var b = "1234";
    if (b = escape(b), null == a || a.length < 8)return void alert("A salt value could not be extracted from the encrypted message because it's length is too short. The message cannot be decrypted.");
    if (null == b || b.length <= 0)return void alert("Please enter a password with which to decrypt the message.");
    for (var c = "", d = 0; d < b.length; d++)c += b.charCodeAt(d).toString();
    var e = Math.floor(c.length / 5), f = parseInt(c.charAt(e) + c.charAt(2 * e) + c.charAt(3 * e) + c.charAt(4 * e) + c.charAt(5 * e)), g = Math.round(b.length / 2), h = Math.pow(2, 31) - 1, i = parseInt(a.substring(a.length - 8, a.length), 16);
    for (a = a.substring(0, a.length - 8), c += i; c.length > 10;)c = (parseInt(c.substring(0, 10)) + parseInt(c.substring(10, c.length))).toString();
    c = (f * c + g) % h;
    for (var j = "", k = "", d = 0; d < a.length; d += 2)j = parseInt(parseInt(a.substring(d, d + 2), 16) ^ Math.floor(c / h * 255)), k += String.fromCharCode(j), c = (f * c + g) % h;
    return unescape(k)
}
function cutString(a, b) {
    var c = "";
    return "" != a && (c = a.length > b ? a.substring(0, b) + "..." : a), c
}
function AddFavorite(a, b) {
    try {
        window.external.addFavorite(a, b)
    } catch (c) {
        try {
            window.sidebar.addPanel(b, a, "")
        } catch (c) {
            alert("\u52a0\u5165\u6536\u85cf\u5931\u8d25\uff0c\u8bf7\u4f7f\u7528Ctrl+D\u8fdb\u884c\u6dfb\u52a0")
        }
    }
}
function SetHome(a, b) {
    try {
        a.style.behavior = "url(#default#homepage)", a.setHomePage(b)
    } catch (c) {
        if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect")
            } catch (c) {
                alert("\u6b64\u64cd\u4f5c\u88ab\u6d4f\u89c8\u5668\u62d2\u7edd\uff01\n\u8bf7\u5728\u6d4f\u89c8\u5668\u5730\u5740\u680f\u8f93\u5165\u201cabout:config\u201d\u5e76\u56de\u8f66\n\u7136\u540e\u5c06[signed.applets.codebase_principal_support]\u8bbe\u7f6e\u4e3a\u2019true\u2019")
            }
            var d = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
            d.setCharPref("browser.startup.homepage", b)
        }
    }
}
function checkStyle(a) {
    if (0 == a)document.getElementById("divDefault").style.display = "block", document.getElementById("divDetails").style.display = "none", document.getElementById("wd").innerHTML = '<span title="ntalker\u652f\u6301" id="wdk_user_' + AccessNsid + '" class="wdk_user_status_span" style="padding-left: 1px; margin-top: 3px; display: block;"> <a alt="\u8ddf\u6211\u804a\u5929" style="padding: 0px; overflow: visible; display: inline;" href="message.shtml"><img border="0" src="http://common.jxnsid.com/Message/tip04no.gif" id="wdk_presence_' + AccessNsid + '" style="display: inline; left: 0px; top: 0px;" name="wdk_presence_image" /></a></span>', document.getElementById("wp").innerHTML = ""; else {
        document.getElementById("divDefault").style.display = "none", document.getElementById("divDetails").style.display = "block", document.getElementById("wd").innerHTML = "", document.getElementById("wp").innerHTML = '<span title="ntalker\u652f\u6301" id="wdk_user_' + AccessNsid + '" class="wdk_user_status_span" style="padding-left: 1px; margin-top: 3px; display: block;"> <a alt="\u8ddf\u6211\u804a\u5929" style="padding: 0px; overflow: visible; display: inline;" href="message.shtml"><img border="0" src="http://common.jxnsid.com/Message/tip04no.gif" id="wdk_presence_' + AccessNsid + '" style="display: inline; left: 0px; top: 0px;" name="wdk_presence_image" /></a></span>';
        for (var b = 1; 5 >= b; b++)b == a ? document.getElementById("a" + b).className = "e" : document.getElementById("a" + b).className = ""
    }
}
function setCookie(a, b) {
    var c = new Date, d = setCookie.arguments, e = setCookie.arguments.length, f = e > 2 ? d[2] : null, g = e > 3 ? d[3] : null, h = e > 4 ? d[4] : null, i = e > 5 ? d[5] : !1;
    null != f && c.setTime(c.getTime() + 1e3 * f), document.cookie = a + "=" + escape(b) + (null == f ? "" : "; expires=" + c.toGMTString()) + (null == g ? "" : "; path=" + g) + (null == h ? "" : "; domain=" + h) + (1 == i ? "; secure" : "")
}
function getCookieVal(a) {
    var b = document.cookie.indexOf(";", a);
    return -1 == b && (b = document.cookie.length), unescape(document.cookie.substring(a, b))
}
function getCookie(a) {
    for (var b = a + "=", c = b.length, d = document.cookie.length, e = 0; d > e;) {
        var f = e + c;
        if (document.cookie.substring(e, f) == b)return getCookieVal(f);
        if (e = document.cookie.indexOf(" ", e) + 1, 0 == e)break
    }
    return null
}
function delCookie(a) {
    var b = new Date;
    b.setTime(b.getTime() - 1);
    var c = getCookie(a);
    document.cookie = a + "=" + c + "; expires=" + b.toGMTString()
}
function ReplyOnblur() {
    var a = document.getElementById("txtMessage");
    "\u53d1\u8868\u610f\u89c1\u8bf7\u5728\u6b64\u5bf9\u8bdd\u6846\u4e2d\u8f93\u5165\uff0c\u6700\u591a\u53ef\u8f93\u5165500\u5b57" == a.value && (a.value = "")
}
function getCount() {
    var a = document.getElementById("count");
    if (void 0 != a && null != a) {
        var b = document.getElementById("txtMessage"), c = 500 - b.value.length;
        if (a.innerHTML = c, 0 > c)return alert("\u8f93\u5165\u5185\u5bb9\u8fc7\u591a"), !1
    }
}
function SetFat(a, b, c, d) {
    var e = document.getElementById(a), f = e.getElementsByTagName("a");
    for (i = 0; i < f.length; i++)f[i].className = c, f[i] == d && (d.className = b)
}
function SetHeights() {
    var a = "MainLeft", b = "MainRight";
    arguments.length > 0 && (arguments[0] && (a = arguments[0]), arguments[1] && (b = arguments[1])), window.setInterval(function () {
        var c = document.getElementById(a), d = document.getElementById(b);
        if (null != c && void 0 != c && "undefined" != c && null != d && void 0 != d & "undefined" != d) {
            c.style.height = "", d.style.height = "";
            var e = c.offsetHeight, f = d.offsetHeight;
            e > f ? d.style.height = "" + (e - 20) + "px" : c.style.height = "" + f + "px"
        }
    }, 400)
}
function ClearContent() {
    var a = ["\u53d1\u8868\u610f\u89c1\u8bf7\u5728\u4e9b\u5bf9\u8bdd\u6846\u4e2d\u8f93\u5165\uff0c\u6700\u591a\u53ef\u8f93\u5165500\u5b57!"], b = document.getElementById("txtMessage") || document.getElementById("txtContent");
    KE ? -1 != a.join("|").indexOf(KE.util.getData("txtMessage")) && KE.util.setFullHtml("txtMessage", "") : -1 != a.join("|").indexOf(b.value) && (b.value = "")
}
function CheckAll(a, b) {
    for (var c = document.getElementById(a), d = c.getElementsByTagName("input"), e = 0; e < d.length; e++)"checkbox" == d[e].type && (b ? d[e].checked = b.checked : d[e].checked = !0)
}
function CancleAll(a, b) {
    for (var c = document.getElementById(a), d = c.getElementsByTagName("input"), e = 0; e < d.length; e++)"checkbox" == d[e].type && (b ? d[e].checked = b.checked : d[e].checked = !1)
}
function GetQueryString(a) {
    var b = new RegExp("(^|&)" + a + "=([^&]*)(&|$)"), c = window.location.search.substr(1).match(b);
    return null != c ? unescape(c[2]) : null
}
function getByteLen(a) {
    for (var b = a.length, c = b, d = 0; b > d; d++)(a.charCodeAt(d) < 0 || a.charCodeAt(d) > 255) && c++;
    return c
}
function getByteVal(a, b) {
    for (var c = "", d = 0, e = 0; e < a.length && (d += null != a[e].match(/[^\x00-\xff]/gi) ? 2 : 1, !(d > b)); e++)c += a[e];
    return c
}
function htmlspecialchars_js(a, b, c, d) {
    var e = 0, f = 0, g = !1;
    ("undefined" == typeof b || null === b) && (b = 2), a = a || "", a = a.toString(), d !== !1 && (a = a.replace(/&/g, "&amp;")), a = a.replace(/</g, "&lt;").replace(/>/g, "&gt;");
    var h = {
        ENT_NOQUOTES: 0,
        ENT_HTML_QUOTE_SINGLE: 1,
        ENT_HTML_QUOTE_DOUBLE: 2,
        ENT_COMPAT: 2,
        ENT_QUOTES: 3,
        ENT_IGNORE: 4
    };
    if (0 === b && (g = !0), "number" != typeof b) {
        for (b = [].concat(b), f = 0; f < b.length; f++)0 === h[b[f]] ? g = !0 : h[b[f]] && (e |= h[b[f]]);
        b = e
    }
    return b & h.ENT_HTML_QUOTE_SINGLE && (a = a.replace(/'/g, "&#039;")), g || (a = a.replace(/"/g, "&quot;")), a
}
function htmlspecialchars_decode_js(a, b) {
    var c = 0, d = 0, e = !1;
    "undefined" == typeof b && (b = 2), a = a.toString().replace(/&lt;/g, "<").replace(/&gt;/g, ">");
    var f = {
        ENT_NOQUOTES: 0,
        ENT_HTML_QUOTE_SINGLE: 1,
        ENT_HTML_QUOTE_DOUBLE: 2,
        ENT_COMPAT: 2,
        ENT_QUOTES: 3,
        ENT_IGNORE: 4
    };
    if (0 === b && (e = !0), "number" != typeof b) {
        for (b = [].concat(b), d = 0; d < b.length; d++)0 === f[b[d]] ? e = !0 : f[b[d]] && (c |= f[b[d]]);
        b = c
    }
    return b & f.ENT_HTML_QUOTE_SINGLE && (a = a.replace(/&#0*39;/g, "'")), e || (a = a.replace(/&quot;/g, '"')), a = a.replace(/&amp;/g, "&")
}
function lengthInUtf8Bytes(a) {
    var b = encodeURIComponent(a).match(/%[89ABab]/g);
    return a.length + (b ? b.length : 0)
}
var Common = {};
Common.AddEvent = function (a, b, c) {
    a.attachEvent ? a.attachEvent("on" + b, function () {
        c.call(a)
    }) : a.addEventListener(b, c, !1)
}, Common.Select = {
    Clear: function (a) {
        a.options.length = 0
    }, Add: function (a, b, c) {
        a.options.add(new Option(c, b))
    }, Text: function (a) {
        return a.options[a.selectedIndex].text
    }, Value: function (a) {
        return a.options[a.selectedIndex].value
    }
}, Common.GetFileInfo = {
    FileName: function (a) {
        return a.empty() ? "" : a.match(/([^\/]+)\.\w+$/)[1]
    }, FullName: function (a) {
        return a.empty() ? "" : a.match(/([^\/]+)\.\w+$/)[0]
    }
}, Common.XmlAttr = function (a, b) {
    try {
        var c = a.selectSingleNode(b);
        if (null != c && 1 == c.nodeType) {
            var d = c.text || c.textContent;
            return d = void 0 == d ? "" : d
        }
        return a.getAttribute(b)
    } catch (e) {
        return ""
    }
}, Common.Selected = function (a) {
    document.getElementById("ulMenu") && (document.getElementById("ulMenu").className = "menulist " + a)
}, String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "")
}, String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "")
}, String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "")
}, String.prototype.empty = function () {
    return "" == this.trim()
}, String.prototype.number = function () {
    return /^[+-]?\d+(\.\d+)?$/.test(this)
}, String.prototype.pic = function () {
    return /^.+.(gif|jpg|jpeg|png|bmp)$/i.test(this)
}, String.prototype.truncated = function (a) {
    var b = this.trim();
    return b.length > a ? b.substring(0, a) : b
}, String.prototype.confirm = function (a) {
    return this.trim() == a.trim()
}, String.prototype.format = function () {
    var a = arguments;
    return this.replace(/\{(\d+)\}/g, function (b, c) {
        return a[c]
    })
};
var Menus = {
    c: null, t: null, cl: null, s: "", Init: function (a, b, c) {
        Menus.c = a, Menus.t = b, Menus.cl = c;
        for (var e = document.getElementsByTagName("ul"), f = null, g = 0; g < e.length; g++)"newscategory" == e[g].className && (f = e[g]);
        containid = a;
        var h = document.getElementById(containid) || f;
        if (Menus.con = h, h)for (var i = h.getElementsByTagName(b), g = 0; g < i.length; g++)i[g].onclick = function () {
            for (k = 0; k < i.length; k++)i[k] == this && (document.cookie = a + "=" + k + "; path=/")
        };
        Common.AddEvent(window, "load", Menus.GetIndex)
    }, Load: function (a, b, c) {
        Menus.c = a, Menus.t = b, Menus.cl = c, containid = a;
        var d = document.getElementById(containid);
        if (Menus.con = d, d)for (var e = d.getElementsByTagName(b), f = 0; f < e.length; f++)e[f].onclick = function () {
            for (k = 0; k < e.length; k++)e[k] == this && (document.cookie = a + "=" + k + "; path=/")
        };
        Common.AddEvent(window, "load", Menus.GetIndex)
    }, GetIndex: function () {
        if (document.cookie) {
            var a = document.cookie.split(";");
            for (i = 0; i < a.length; i++) {
                var b = a[i].split("=");
                if (b[0].toString().trim() == Menus.c) {
                    var c = b[1], d = Menus.con;
                    if (d) {
                        for (var e = d.getElementsByTagName(Menus.t), f = 0; f < e.length; f++)f != c && (e[f].className = "");
                        e[c] && (e[c].className = Menus.cl)
                    }
                }
            }
        }
    }
};
Common.GetUserFace = function (a, b, c) {
    if ("" != a && null != a && c)return a.replace("Org", c);
    switch (b) {
        case"0":
            a = "http://common.jxnsid.com/common/defaultface/member_m110X110.jpg";
            break;
        case"1":
            a = "http://common.jxnsid.com/common/defaultface/party146X146.gif";
            break;
        case"2":
            a = "http://common.jxnsid.com/common/defaultface/Ent86X47.jpg";
            break;
        case"3":
            a = "http://common.jxnsid.com/common/defaultface/Org110X110.gif";
            break;
        default:
            a = "http://common.jxnsid.com/common/defaultface/party146X146.gif"
    }
    return a
};