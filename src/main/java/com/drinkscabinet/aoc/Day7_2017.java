package com.drinkscabinet.aoc;

import java.io.BufferedReader;
import java.io.StringReader;
import java.lang.management.BufferPoolMXBean;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7_2017 {

    private static Map<String, Node> nodeMap;

    private static class Node {
        String name;
        int weight;
        Integer totalWeight;
        Set<String> children = Collections.emptySet();
        String parent;
        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", children=" + children +
                    ", parent=" + parent +
                    '}';
        }

        public int getTotalWeight() {
            if( totalWeight == null ) {
                totalWeight = weight + children.stream().mapToInt(s -> nodeMap.get(s).getTotalWeight()).sum();
            }
            return totalWeight;
        }

        public Stream<Node> children() {
            return children.stream().map(s -> nodeMap.get(s));
        }

        public boolean isBalanced() {

        }
    }

    private static final Pattern p = Pattern.compile("^(\\w+) .(\\d+).");
    private static Node parse(String l) {
        // ugml (68) -> gyxo, ebii, jptl
        Matcher m = p.matcher(l);
        if( m.find()) {
            Node n = new Node();
            n.name = m.group(1);
            n.weight = Integer.valueOf(m.group(2));
            // children
            int i = l.indexOf("->");
            if( i > 0 ) {
                n.children = Arrays.stream(l.substring(i+3).split(",")).map(String::trim).collect(Collectors.toSet());
            }
            return n;
        }
        throw new RuntimeException("failed " + l);
    }

    public static Node root(Node n) {
        if( n.parent == null) return n;
        return root(nodeMap.get(n.parent));
    }



    public static void main(String[] args) {
        System.out.println(parse("ugml (68) -> gyxo, ebii, jptl"));

        BufferedReader r = new BufferedReader(new StringReader(input));

        nodeMap = r.lines().map(Day7_2017::parse).collect(Collectors.toMap(n -> n.name, n -> n));
        System.out.println(nodeMap);
        // Now find parents
        for (Map.Entry<String, Node> n : nodeMap.entrySet()) {
            for (String child : n.getValue().children) {
                nodeMap.get(child).parent = n.getValue().name;
            }
        }
        System.out.println(nodeMap);

        Node root = root(nodeMap.get("lwuxii"));
        System.out.println(root);
        System.out.println(root.getTotalWeight());
        root.children.forEach(s -> System.out.println(nodeMap.get(s).getTotalWeight()));

    }

    private static final String input = "tqefb (40)\n" +
            "lhrml (164) -> ecblhee, sdjshz\n" +
            "ykntwjm (16)\n" +
            "fbebcq (233) -> ilzfg, vqbvnf, idyiyg, tifpswp\n" +
            "rqjpza (1043) -> xszbzi, zafhcbb, qoouyiw\n" +
            "zazkyd (203) -> vzylhou, ndvkjn\n" +
            "ndfxn (48) -> brxmlaa, nlbvsaj\n" +
            "pfjpsxf (1714) -> uchxwm, ohpvb\n" +
            "tnuvu (395)\n" +
            "ccxsuk (12)\n" +
            "rrhbmgi (98)\n" +
            "vfkeogg (58)\n" +
            "xfbvid (86)\n" +
            "muburl (57)\n" +
            "xxpnqpc (224) -> ksuydd, pmxdc\n" +
            "ilkrxa (9)\n" +
            "lkjkrj (81)\n" +
            "lcuhsxu (75)\n" +
            "flcyx (32)\n" +
            "nuuvgid (23)\n" +
            "ltweydr (11)\n" +
            "pafuvtn (72)\n" +
            "simlgvw (88)\n" +
            "npjbfxb (94)\n" +
            "ilzfg (31)\n" +
            "ingzgv (199) -> brxtyug, nfexc\n" +
            "cejlti (341)\n" +
            "boslv (10868) -> ygeeepa, asiigv, jiorqgn, fnbpxoq\n" +
            "dqkxvd (112) -> wkbqh, dnirvcs\n" +
            "sczzyjo (167) -> euaku, ytgdbn\n" +
            "uwnwx (171) -> czmxofi, tmmbh, engsa\n" +
            "gxlcwkl (34)\n" +
            "suwehi (15)\n" +
            "gkpirl (162) -> vbrwu, tnjkbcd\n" +
            "dpahgkv (52)\n" +
            "pbebtfc (114) -> bubcg, vaiqef\n" +
            "ymnissn (47)\n" +
            "mltftcq (21)\n" +
            "rejewfv (71) -> rfuqci, oyuep, lfveq\n" +
            "grbnbad (199) -> mltftcq, gepue\n" +
            "kuwuy (97)\n" +
            "rzhwl (1936) -> vmlmrk, ushmwu, tziitox\n" +
            "dbqujc (87)\n" +
            "texjaoa (104) -> mpkijdc, lyqbnl\n" +
            "wpxaup (975) -> jijmyu, ejjdfwk, jsrmk, ofshfcn\n" +
            "exqxh (31)\n" +
            "fmtzdw (37)\n" +
            "vmbeqe (33)\n" +
            "fxaom (41)\n" +
            "ewowmhn (63) -> xyioev, qbepv, rqbld\n" +
            "lgpsy (73)\n" +
            "fjgns (149) -> tmbuahz, wvtlu\n" +
            "cyyjrf (56) -> gmobt, hlsvyhx, ngwdz, hrrzu\n" +
            "dntxe (99)\n" +
            "nafrtm (2458) -> gpkgkv, vuqcpir, jvegsv, xhorr, rzsxtev, xksfmz\n" +
            "lebuxmo (14)\n" +
            "fbfqvq (29)\n" +
            "zcegp (98)\n" +
            "clfvfbd (167) -> wnbbrab, bdmmi\n" +
            "wfaus (13)\n" +
            "gepue (21)\n" +
            "lmrhhj (27)\n" +
            "roprp (6)\n" +
            "itimbmg (71)\n" +
            "fepdsz (16)\n" +
            "phcizz (93) -> olmzxu, efkixrq\n" +
            "vuqcpir (1823) -> uljiu, qignfgc\n" +
            "sbvopw (49)\n" +
            "vebuhzt (136) -> eyfds, nhylqxq\n" +
            "rdssp (44)\n" +
            "lipbj (68)\n" +
            "mjfnukc (83) -> sbvopw, kgclit\n" +
            "ltfxsl (97)\n" +
            "wkbqh (81)\n" +
            "xbnbi (32)\n" +
            "vgffruy (98) -> yaueae, lpflvwn, qirikp, xzjcbwa, kshtume, voanano\n" +
            "lcxeysi (275) -> pfqyoho, mnjhqkb\n" +
            "nadgwb (87)\n" +
            "lycks (6)\n" +
            "nyghbkz (10)\n" +
            "racbcat (78)\n" +
            "hhtpv (19)\n" +
            "kjonnd (128) -> vfuywa, zttbwuj\n" +
            "dnpbek (219) -> cbwrev, nrtmf\n" +
            "wrrdy (55)\n" +
            "khumo (37)\n" +
            "xhoytye (85)\n" +
            "wpulnl (98)\n" +
            "uxmjz (38)\n" +
            "oqpxd (56)\n" +
            "iztdlnf (7)\n" +
            "mofllk (131) -> lyfsi, rybovf\n" +
            "fmzpc (21)\n" +
            "yysvm (89)\n" +
            "boropxd (4285) -> slzaeep, hiotqxu, qppggd, iahug, cwwwj, upfhsu, jjlodie\n" +
            "hqbunkc (88)\n" +
            "dteoa (52)\n" +
            "dhugaw (145) -> gdbut, gkbmdh\n" +
            "izvfjt (89)\n" +
            "nfomnyt (9)\n" +
            "gbyblhe (75)\n" +
            "mjkgsg (97)\n" +
            "vkgbz (79)\n" +
            "ldfun (87)\n" +
            "ttfhrp (82)\n" +
            "vjaffbt (391) -> cdqpv, pavbfb\n" +
            "jgrigjx (24)\n" +
            "upjvowk (87)\n" +
            "dfmrhz (31)\n" +
            "qqrqid (82)\n" +
            "kbqpd (30)\n" +
            "wqtcnyz (63)\n" +
            "kwfghyi (7)\n" +
            "ajacjz (174) -> mthaau, ifogeft\n" +
            "pglsa (129) -> wimpgm, wtcuxsp\n" +
            "eakfc (45)\n" +
            "jlhly (16)\n" +
            "vbrpgbe (96)\n" +
            "ydpxkx (8)\n" +
            "zpqlfyn (80) -> skxcsro, uelcs\n" +
            "pbonnr (208) -> kpxdh, izgkl, nquecpp\n" +
            "obxki (9)\n" +
            "drenb (85)\n" +
            "vozeer (10879) -> rejewfv, fnkxzn, xxardb\n" +
            "yixmif (84)\n" +
            "psdcvl (202) -> lgbltl, oriskzl, zazkyd, synlyuo, mwtqk, oryvzmp\n" +
            "spnkurd (66)\n" +
            "rxcyz (80) -> nbonyx, hmrbytx\n" +
            "kjbps (23)\n" +
            "qkbflxg (94)\n" +
            "hywytz (162) -> amrvw, gzxpx\n" +
            "sknxjo (59)\n" +
            "cnnajhy (80)\n" +
            "uiqzhqu (84)\n" +
            "omtrskm (17)\n" +
            "pnbzj (60) -> vbrpgbe, cuupma, yddiq\n" +
            "jiybk (49) -> nnmcrj, vsyhi\n" +
            "cnyyygq (54)\n" +
            "tekqjru (6)\n" +
            "qgnkn (66)\n" +
            "qvstpyq (89)\n" +
            "ntgdq (48)\n" +
            "phltmve (13)\n" +
            "anprhpf (70)\n" +
            "epnfp (39)\n" +
            "lxdlq (48)\n" +
            "yuiqntx (55)\n" +
            "oyuep (270) -> xsdlmg, xvbezu\n" +
            "hobchva (97)\n" +
            "qtwaod (228) -> tdxve, dsoipkq\n" +
            "draxijw (67)\n" +
            "evkyf (15)\n" +
            "sipdbpd (82)\n" +
            "vlxqvi (266) -> dzbqu, qnmgp\n" +
            "eelrlu (36)\n" +
            "opilymx (35)\n" +
            "buzsiye (16)\n" +
            "sabkxwy (189) -> zddnqld, gomrr\n" +
            "fwwvg (88)\n" +
            "teafht (87)\n" +
            "zzlgja (27) -> boclo, eywfs, tskeqa, gbyblhe\n" +
            "ajjyaog (327)\n" +
            "worqvps (32)\n" +
            "illzp (29)\n" +
            "tifpswp (31)\n" +
            "jzeeng (88)\n" +
            "havmool (10)\n" +
            "wnbbrab (61)\n" +
            "dwtgak (1742) -> yolqpau, paetkc, wjpvpg\n" +
            "ieejg (255) -> islbpsv, fcfujgr\n" +
            "pjrtj (64)\n" +
            "idqfawa (1327) -> osfsey, mfhtd, owbhv\n" +
            "dntzv (9)\n" +
            "ihmixw (93)\n" +
            "upvzg (45)\n" +
            "cvmlcyx (65) -> lkjkrj, dslabn\n" +
            "cjhxpn (9)\n" +
            "hjhaae (44)\n" +
            "axstuln (9)\n" +
            "tcymb (93)\n" +
            "hmrbytx (46)\n" +
            "jwrcrra (37)\n" +
            "ltfsqn (95)\n" +
            "vktrdf (79)\n" +
            "xyioev (67)\n" +
            "wdjpqq (43)\n" +
            "lukls (78)\n" +
            "rhinivh (56)\n" +
            "yxxrv (49)\n" +
            "rodfe (116) -> svxrmhl, fpqfvd\n" +
            "wtcuxsp (31)\n" +
            "ewlryhh (5)\n" +
            "lqorouz (721) -> kgxmev, zrrklpo, lazovhv, cajloqp, jstihz\n" +
            "anendx (90)\n" +
            "ghbmnod (61)\n" +
            "pkhhbp (452)\n" +
            "owaqfb (84)\n" +
            "uwgomw (260)\n" +
            "wxcas (256) -> qrzofif, vdexe\n" +
            "tvzbk (66)\n" +
            "jmpyaib (69) -> rhinivh, uplalh\n" +
            "ffzpfp (64)\n" +
            "clxasb (66)\n" +
            "dnaclwb (12)\n" +
            "ixdxwhj (89)\n" +
            "jzklwrz (1218) -> ocspnqg, zrmtgk, dfxox, qpbqzwi, uwgomw, hobrps\n" +
            "pmkcwxd (81) -> ksizuia, ayrmwy, uutzk\n" +
            "utsyk (5)\n" +
            "zttbwuj (43)\n" +
            "vzhrt (9)\n" +
            "poywgwi (419)\n" +
            "eelwu (83) -> ttfhrp, sxagng, lgpqna, qqrqid\n" +
            "mgmmqpf (50)\n" +
            "ecrkcs (66)\n" +
            "nmlmm (97)\n" +
            "akzwfo (11)\n" +
            "zxymd (206) -> gyxopp, ychlsfn\n" +
            "lxeeq (26)\n" +
            "pkrbgb (88)\n" +
            "hnfjb (13)\n" +
            "koqnz (95)\n" +
            "aowql (5)\n" +
            "engsa (81)\n" +
            "gmobt (96)\n" +
            "nexgspx (78)\n" +
            "humrsd (35)\n" +
            "bwpvsv (8)\n" +
            "tuxctb (97)\n" +
            "iqefm (211) -> vftol, haphukg\n" +
            "llmhaad (156) -> bkzfd, irxbhi\n" +
            "pdyjzd (142) -> zfjuv, hqbunkc, ghkyui\n" +
            "sjvdfk (89) -> zvefixb, ftomb\n" +
            "hfhqvb (2026) -> sqsyxpe, iaeemxc, vuzuq\n" +
            "puzsftr (67)\n" +
            "gachpm (41)\n" +
            "fhifg (1924) -> lkfcgw, magml, tjrdifv, sefzkr\n" +
            "nzwdyjh (48)\n" +
            "idfja (47)\n" +
            "kiueoo (181)\n" +
            "yhmclb (87)\n" +
            "riuhtpn (101) -> ydapn, wuqhqm\n" +
            "tsasgm (13)\n" +
            "mjnjzet (153) -> mopevhs, hvyscq, ugtpfof, qnbxo, qerhwfp, xojyah\n" +
            "tfwcyid (293) -> ajqaniw, accqde, fwzkvc\n" +
            "hvigowx (97) -> hujizfz, eoyva, sknxjo\n" +
            "bdudb (50)\n" +
            "jocdav (91)\n" +
            "egoaw (5)\n" +
            "fuaaot (91)\n" +
            "suslpgz (116) -> zvldmx, bjtca\n" +
            "htxwgxj (36)\n" +
            "alsrt (189) -> dvpjab, ipwdq\n" +
            "ndgra (135) -> ukzms, hrrxs, cilwhdb\n" +
            "cyzobwd (21)\n" +
            "nozqe (42)\n" +
            "boclo (75)\n" +
            "niiwne (50)\n" +
            "otsaip (35)\n" +
            "ljpyq (32)\n" +
            "wvtlu (93)\n" +
            "oryvzmp (227) -> mcihl, buhmvt\n" +
            "oijissd (23)\n" +
            "uzowuwt (44)\n" +
            "vmvnrq (97)\n" +
            "qwqenwn (96) -> rrhiz, pqeso, evliz, uvvsl\n" +
            "tmmbh (81)\n" +
            "accqde (92) -> kdbbkg, vzcyoxl\n" +
            "oufzuw (89)\n" +
            "hqgbfvn (68)\n" +
            "rxmxfno (76)\n" +
            "ozmoirq (107) -> oitkdg, xsbjwz\n" +
            "mefsxl (5015) -> ixlerg, uoujbtv, khjbclb\n" +
            "qirikp (91) -> koqnz, mbora, ltfsqn\n" +
            "jlpfu (144) -> avrfz, ywuqf\n" +
            "dkuds (97)\n" +
            "inggl (261) -> qgudtwk, myfcqxe\n" +
            "tixcp (14140) -> qoyhxl, jgvsp, zwtnf\n" +
            "kyspusd (25)\n" +
            "vdxdq (68)\n" +
            "qiqvce (72)\n" +
            "ehgnylr (8)\n" +
            "nrtmf (15)\n" +
            "xavay (8) -> fibcigx, omvflhe\n" +
            "socuv (6510) -> tjvry, ybqehu, mapirt\n" +
            "sdhtcgx (388)\n" +
            "ogksb (78)\n" +
            "tuytxy (65) -> nteuv, jsdzajv\n" +
            "hyvhosf (25)\n" +
            "kpdms (44)\n" +
            "mswjlev (107) -> jfdud, knpefcs\n" +
            "ckrqr (30)\n" +
            "jeejn (82)\n" +
            "idgrh (20)\n" +
            "dnirvcs (81)\n" +
            "jbutmk (109) -> qgnkn, tswgpqg\n" +
            "lwuxii (25)\n" +
            "uhmypt (39)\n" +
            "swkfz (92)\n" +
            "ugzxbjw (72)\n" +
            "ccyflwf (111) -> spnkurd, slbun\n" +
            "xxcpmu (89)\n" +
            "gworrlc (14712) -> aaizfj, nqayozt, xftjd, whpjefc, dnpbek\n" +
            "qdkcyj (40)\n" +
            "erzpzd (66)\n" +
            "alpoep (51) -> wammii, svpwz\n" +
            "nteuv (89)\n" +
            "uwmocg (72774) -> npwjod, fmqxggg, tkaax, tbpyoxy, mefsxl, hhxofiu\n" +
            "odpuf (15)\n" +
            "sxagng (82)\n" +
            "fcxag (57)\n" +
            "kebxaum (31)\n" +
            "ahxse (331) -> qwtss, stqsnlz, bgibn, vjrrptz, ehqap, pdawj\n" +
            "wvyht (26)\n" +
            "ockjo (29)\n" +
            "aiqug (46)\n" +
            "ejehv (1135) -> owxcxd, nvbococ, pmkcwxd\n" +
            "invkz (94)\n" +
            "yrdsxpn (12)\n" +
            "gvjwj (23)\n" +
            "itfiqj (163)\n" +
            "gimjz (201) -> vaxierg, feope\n" +
            "ekzro (71)\n" +
            "zbscr (378) -> myfkdkl, wtpgz\n" +
            "jmrry (87)\n" +
            "xfwju (45) -> wnteyab, zxmpvm\n" +
            "jpsoy (1570) -> sewtbk, mofllk, txhcyqf, tkntd, oqqnazv\n" +
            "efmrpp (78)\n" +
            "odqkxi (6) -> gvjuwwn, quhyxh, dqkxvd, misjd, vmnys, rtgpmf, lhrml\n" +
            "blafijn (262) -> lwuxii, qfmofym\n" +
            "yqcwdh (80) -> rfmbo, frvqvlo\n" +
            "hhjgskh (7)\n" +
            "idxgock (23)\n" +
            "ipgrte (66)\n" +
            "zpxbc (118) -> cletkls, krvtfsj, budohp\n" +
            "yjqdgq (30)\n" +
            "vgfve (9)\n" +
            "uclzjin (73)\n" +
            "pqeso (116) -> xgxea, gvjwj, grksx\n" +
            "fmqxggg (6539) -> jzlquwl, oipjur, slvkou, pjpfvqe, gcjdpx, ebtkonx\n" +
            "ytjuerk (23)\n" +
            "vpgxh (85)\n" +
            "mnepu (75)\n" +
            "zwjuqr (26)\n" +
            "mtjcqtg (52)\n" +
            "djzbom (89)\n" +
            "iwgtul (79)\n" +
            "hwvkbv (47)\n" +
            "olmzxu (10)\n" +
            "buplvox (42) -> xzxsnus, azpwos\n" +
            "fscqnmn (77)\n" +
            "xeblqw (55)\n" +
            "lazovhv (153) -> deagdif, jasamk\n" +
            "mopos (62)\n" +
            "sewtbk (123) -> zddbivo, esyatlt\n" +
            "ifhntf (8)\n" +
            "uxxwj (215) -> gkuqwak, qqesw\n" +
            "hqesnp (58)\n" +
            "ncdnc (34)\n" +
            "psmlok (55) -> hbabeiy, xcfvrz, lujwkxl, tgtwgnl, ueacv, fqorpj, ennlusa\n" +
            "awnos (97)\n" +
            "ojlgsc (74)\n" +
            "kshtume (300) -> jatvuw, uxmjz\n" +
            "qidhm (4140) -> svgfqs, zyuuri, ylysm, psdcvl, pfjpsxf, pduiy\n" +
            "pfsgds (86)\n" +
            "rzsxtev (419) -> kqiupp, gxgnzi, glveid, iyqzno, fhfcmv, crxpc\n" +
            "jbhoa (52)\n" +
            "pcciu (40)\n" +
            "urvwum (170) -> ytmnrjw, fkcwv\n" +
            "bkzfd (96)\n" +
            "jldxskr (14)\n" +
            "hatujsc (93)\n" +
            "droaqx (29)\n" +
            "fohnt (22)\n" +
            "czwrn (1100) -> ykoyzh, wuavkqo, tvgfdb\n" +
            "kvmjx (850) -> mjryqy, evofre, gfkxqmk\n" +
            "sqdyn (61)\n" +
            "weelykw (46) -> vqmckky, hzxgz\n" +
            "yiipi (27)\n" +
            "dhltapo (98)\n" +
            "qsetl (310) -> isesfdw, fsdvpk\n" +
            "ctpac (68)\n" +
            "ycnqav (5)\n" +
            "uplalh (56)\n" +
            "xpgzx (48) -> zkpghh, eawuhm\n" +
            "hlabv (1742) -> qxvfq, vpesyi, untiezr\n" +
            "ifogeft (21)\n" +
            "vpqmdqf (55)\n" +
            "lzfuzm (32)\n" +
            "gszkqio (79)\n" +
            "gomrr (76)\n" +
            "mzmyto (45)\n" +
            "zcohp (41)\n" +
            "haazrx (21)\n" +
            "eodye (116) -> eaxlxoy, sbovaf, fkawsxk, uvfhx\n" +
            "xxzzr (85)\n" +
            "ybqehu (16) -> qtwaod, uwnwx, vzgkr, aywwpfz, kpbdkq, hbjrjw, sxlkxs\n" +
            "vaale (90) -> idfja, hwvkbv\n" +
            "grqfud (823) -> wopvp, ccyflwf, usmhoj, hlnram, oyyfhxh, bneqef\n" +
            "hrrzu (96)\n" +
            "ceibzne (23)\n" +
            "jmnhj (8)\n" +
            "esyatlt (38)\n" +
            "vhgtr (24)\n" +
            "ffakxgn (58)\n" +
            "dcdxxv (21)\n" +
            "zvefixb (69)\n" +
            "shwph (30)\n" +
            "vuzuq (23) -> tbhdon, suakgk\n" +
            "nmber (37)\n" +
            "pjpfvqe (8) -> arhfcx, hgqsp\n" +
            "arugmz (67) -> eflbhmn, gekwq\n" +
            "tvunonh (89)\n" +
            "ndpweps (99) -> wuuxzqy, jyuvjzz, xdgbnoj\n" +
            "fxrkpax (55)\n" +
            "voanano (320) -> zfavz, smttd\n" +
            "szwdc (8)\n" +
            "bsqbqtw (31)\n" +
            "xvjmz (155) -> aktlwea, jxszqd\n" +
            "azzpq (153) -> ssngqqp, lebuxmo\n" +
            "qndtc (43)\n" +
            "dvsntva (37)\n" +
            "vzgkr (294) -> rguht, lnxxnux\n" +
            "phqtbfn (38)\n" +
            "zhiacn (16)\n" +
            "oseerig (410) -> haazrx, aqcscz\n" +
            "fagsd (303) -> hrciraz, lnnhuld\n" +
            "mpjvq (34) -> pfsgds, xfbvid, iebfp, xmhokd\n" +
            "bgibn (75) -> vmbeqe, ikdpgy\n" +
            "wqbwqi (66)\n" +
            "vpesyi (204)\n" +
            "tvfhovv (20)\n" +
            "tjnerh (2780) -> ahhqrp, rlnee\n" +
            "sjyymi (78)\n" +
            "xgxea (23)\n" +
            "nqayozt (69) -> mruoew, vogdr, ihquo, ngbto\n" +
            "diwwjq (43)\n" +
            "msrlpql (68)\n" +
            "wnteyab (67)\n" +
            "tzumnfw (1029) -> nttyljj, rwmqvs\n" +
            "wammii (44)\n" +
            "ggttj (237) -> ckrqr, kbqpd\n" +
            "zddbivo (38)\n" +
            "ulymdk (84)\n" +
            "qbjpr (41)\n" +
            "wfnrlgo (12)\n" +
            "wxeki (6)\n" +
            "jijmyu (274)\n" +
            "socgh (50)\n" +
            "eiceooz (412)\n" +
            "jauxc (17) -> tuxctb, utnjrpg\n" +
            "klflx (19)\n" +
            "ueacv (192) -> ygzvz, wgbupli\n" +
            "yzfbc (45)\n" +
            "rocyko (218) -> rgzrfak, trabc\n" +
            "djblaef (79)\n" +
            "tdxve (93)\n" +
            "fpwfhqa (317) -> ocxbu, bqzya, wevkbue, roprp\n" +
            "dslabn (81)\n" +
            "eqzjl (185) -> albxmd, fdetm, ctjyra\n" +
            "hjwug (13)\n" +
            "vfuywa (43)\n" +
            "rtjax (93)\n" +
            "tuuqm (128) -> sokar, wtjzsm\n" +
            "xuxuy (224) -> qkbflxg, tvlhxbd\n" +
            "dzbqu (28)\n" +
            "hldjtya (48) -> xxzzr, navzdpq, vgbxwsh, xhoytye\n" +
            "vohmteg (10)\n" +
            "uxuwgmz (8379) -> lhopv, vexiqv, tjnerh\n" +
            "yitoepf (20)\n" +
            "vpmyvzz (11)\n" +
            "sinfhwq (219) -> tsdpmhr, xcaqylz\n" +
            "betkfui (84)\n" +
            "vqanko (399) -> ygcph, dbqlqa, oawaasr, ajacjz\n" +
            "lkfhfri (23)\n" +
            "zpdcajt (44)\n" +
            "ebtkonx (158) -> fmdxpvz, fdgvmi\n" +
            "milnj (9)\n" +
            "ytacid (51) -> upjvowk, sgmtal, ldfun\n" +
            "uzasvyd (66)\n" +
            "cbjyf (15)\n" +
            "clbkb (15)\n" +
            "bqzya (6)\n" +
            "oiyohma (24)\n" +
            "dohxzvo (752) -> vuxcbe, lczpqwp, pglsa, jntictz\n" +
            "qbnwlwe (67)\n" +
            "myfcqxe (67)\n" +
            "uqcjwp (52)\n" +
            "srofdd (90) -> rtjzffy, qbjpr\n" +
            "cprxbbf (1657) -> dpqsg, myaznzp, yonamnd\n" +
            "ibfxt (25)\n" +
            "lhcix (265) -> jxmvpr, lagoq, bwpvsv, ehgnylr\n" +
            "wuluv (27912) -> atgrdn, hdpqtg, qidhm, socuv, boslv, fcakejv\n" +
            "wuuxzqy (45)\n" +
            "sfevya (11)\n" +
            "jjlodie (46) -> jtxvw, cjwnq, mtpbt\n" +
            "vounq (42) -> ounsg, rbhzdm, serls, ygqzh\n" +
            "syzptux (14)\n" +
            "aptrqbk (20)\n" +
            "gznxb (15)\n" +
            "eevia (89)\n" +
            "emdolt (93)\n" +
            "kukkmrx (223)\n" +
            "nmdit (71) -> qcaxee, cmtpu\n" +
            "whfwtio (60)\n" +
            "zwxkyn (85)\n" +
            "mamhdgl (43)\n" +
            "zbtgzh (52) -> zeien, gszkqio, iwgtul\n" +
            "pfqyoho (57)\n" +
            "oawaasr (158) -> jpadrxu, ylepv\n" +
            "gahopg (64)\n" +
            "dobuzav (11) -> tmzvfia, ihmixw, emdolt, hhqks\n" +
            "eieqwo (26)\n" +
            "qvvgle (20)\n" +
            "mcponby (11)\n" +
            "dqabk (41)\n" +
            "zxcjlk (2139) -> llmhaad, oykcbpj, pnbzj\n" +
            "pmosxof (128) -> kgiswh, flspx, qszzvq\n" +
            "bjkfl (96)\n" +
            "fcpmz (21)\n" +
            "maykdnd (86)\n" +
            "idyiyg (31)\n" +
            "vzcyoxl (22)\n" +
            "gwfkzhk (43) -> qbnwlwe, draxijw\n" +
            "qmnjlw (95)\n" +
            "azpwos (54)\n" +
            "slzaeep (1023) -> edshwfr, emuysfg\n" +
            "yusdv (45)\n" +
            "cohgcil (41)\n" +
            "bkjhdgy (72)\n" +
            "nqjsdi (20)\n" +
            "oivlxtz (9)\n" +
            "ocowqvd (7522) -> vgffruy, ykjng, hlabv\n" +
            "lujwkxl (54) -> bkcozk, bcxne, mvhybn, ipgrte\n" +
            "wuqhqm (41)\n" +
            "vaziim (27)\n" +
            "wcgfkq (630) -> zermehr, tfaxln, zvtsnz, rukmtyd\n" +
            "ciwydtl (65) -> ezzzomx, fmele, luxtn, uclzjin\n" +
            "ndvkjn (36)\n" +
            "kunzaag (2592) -> mgvovnb, ftntagd, ggmjft\n" +
            "sopjux (81) -> tddqw, iumspgx, ojsntix, uxuwgmz, ehwofnh, zrtrhph, rujdblh\n" +
            "ifengok (20)\n" +
            "hlsvyhx (96)\n" +
            "amzaq (59) -> jmrry, plexk\n" +
            "ehqap (141)\n" +
            "pzoji (47)\n" +
            "tebbb (25)\n" +
            "oglut (40)\n" +
            "zwtuhb (187) -> opilymx, racquh\n" +
            "brxjw (120) -> bkjhdgy, pafuvtn\n" +
            "hlfvf (53)\n" +
            "gkctmh (31)\n" +
            "glveid (267)\n" +
            "fodfvds (77)\n" +
            "ngffuwc (123) -> jjkyj, xczaehv, xpuly, qqdrox\n" +
            "noksmmp (21)\n" +
            "vhzoa (84)\n" +
            "txhcyqf (91) -> cnyyygq, iuuhcc\n" +
            "aauxa (69)\n" +
            "upfhsu (27) -> jukhdvi, lilufvg, rodfe, hvigowx\n" +
            "brxtyug (71)\n" +
            "ciizgft (55)\n" +
            "ebcls (73)\n" +
            "jsrmk (7) -> yukbvwy, tvunonh, yysvm\n" +
            "cletkls (82)\n" +
            "yaqlguh (9)\n" +
            "jylvi (535) -> kjonnd, fisyh, ayuwm\n" +
            "opkren (49)\n" +
            "mukkakg (33)\n" +
            "xjkzvpn (66)\n" +
            "wbydzcm (686) -> ahtqrem, iwbzsq, jafvw, cyyjrf\n" +
            "gdbut (24)\n" +
            "zcptsvh (1651) -> qmwbv, kiueoo, biner, mjfnukc, anlopt\n" +
            "msajn (129) -> qasbblg, yuiqntx\n" +
            "pvowck (43)\n" +
            "deagdif (96)\n" +
            "cdblpel (97)\n" +
            "ojdlcls (78)\n" +
            "ozackw (51)\n" +
            "lkfcgw (40) -> ckpsa, jqkrk\n" +
            "xhzdo (98)\n" +
            "rlnee (55)\n" +
            "zprakvq (213) -> ixruqck, vohmteg, havmool\n" +
            "jcpbsc (36)\n" +
            "knnmsai (24)\n" +
            "cilwhdb (21)\n" +
            "kkeqyo (47)\n" +
            "kzjdhi (48)\n" +
            "ytvsrny (59)\n" +
            "jfdud (68)\n" +
            "jpyfzy (31) -> xjdxfb, hclhewh\n" +
            "djwizu (46)\n" +
            "vrghbl (94)\n" +
            "qjdzqm (144) -> saflt, whfwtio\n" +
            "mruoew (45)\n" +
            "ptvuev (302) -> yplyevo, dptzp\n" +
            "xnjod (73)\n" +
            "xxardb (1219) -> ifhntf, pulfop\n" +
            "qaosty (9)\n" +
            "qydug (7) -> xhyph, ogksb, efmrpp\n" +
            "igfvkh (45)\n" +
            "hablmj (310) -> jyjvxw, ilkrxa\n" +
            "ugtpfof (302) -> uhmypt, sdhvw\n" +
            "kbclwl (98)\n" +
            "dhhcmdj (1815) -> glwvlc, escef, weelykw\n" +
            "uembj (90)\n" +
            "mvrpr (97)\n" +
            "ahtqrem (358) -> cohgcil, pkejtl\n" +
            "ryxauy (28) -> slaiad, njogs\n" +
            "xhpbaer (32) -> zgjabut, oivlxtz\n" +
            "ygqzh (40)\n" +
            "jxszqd (43)\n" +
            "pgozvs (44)\n" +
            "lislxi (80)\n" +
            "olosbi (28)\n" +
            "fawadg (73)\n" +
            "ssngqqp (14)\n" +
            "xjhfgv (81)\n" +
            "vgtvj (52)\n" +
            "gkuqwak (14)\n" +
            "mnrsu (96) -> dvnzgo, slkkm, xxcpmu, izvfjt\n" +
            "ppmjk (289)\n" +
            "aqviuy (6)\n" +
            "kqiupp (157) -> vbysnql, xyfclte\n" +
            "xzjcbwa (376)\n" +
            "svgfqs (1150) -> lgpgt, ndpweps, qwqwp\n" +
            "fibcigx (82)\n" +
            "vjrrptz (60) -> vwzfk, vaziim, fwoavaz\n" +
            "yolqpau (641) -> nhefqs, pfudc, gdpmx\n" +
            "suzkkr (2330) -> ezilows, ndfxn, vwledx\n" +
            "xvbezu (59)\n" +
            "zgltwud (74)\n" +
            "xojyah (84) -> ojlgsc, ghbhqnl, ztngdq, wwtvhb\n" +
            "tfqel (49) -> vmvnrq, mvrpr, awnos, ezbax\n" +
            "wwwep (20)\n" +
            "tpcmp (87) -> mwnczjf, ylkwbm\n" +
            "ytmnrjw (71)\n" +
            "fqorpj (174) -> cltigxi, ntgdq, lehkqty\n" +
            "vuydl (30)\n" +
            "xtcpynj (31920) -> tixcp, wlkzwch, sclfvp, nafrtm, vozeer, ocowqvd\n" +
            "mvice (298) -> ftnzxce, mkuow\n" +
            "ahotpd (80)\n" +
            "ylkwbm (46)\n" +
            "nxtwjc (17)\n" +
            "eolzkkc (24)\n" +
            "juahc (264) -> vrghbl, bdlkkll\n" +
            "cmuclkp (93)\n" +
            "gfhrh (50)\n" +
            "txbkss (72)\n" +
            "ehpzl (87) -> nzwdyjh, lxdlq\n" +
            "dbqlqa (38) -> csmgexg, djzbom\n" +
            "eiynck (41)\n" +
            "eaxlxoy (84)\n" +
            "izllv (121) -> hohmn, oufzuw, ixdxwhj\n" +
            "ohgyus (42)\n" +
            "adkszk (70)\n" +
            "rxbohqw (75) -> qubxyhi, lipbj, kvfwsoc, vdezdp\n" +
            "ylepv (29)\n" +
            "myfhxk (38) -> cyenkm, elqgxm, uxjzpl, ujdhpxh, ukzcbat, ykphap\n" +
            "jaxczdp (25)\n" +
            "fgjcea (19)\n" +
            "ezcmxxy (245) -> yjffdl, esroty\n" +
            "adzhm (55)\n" +
            "mtpbt (152) -> lrxhito, fvdoc, aauxa\n" +
            "hvche (95)\n" +
            "aywgi (5)\n" +
            "jixdvf (7598) -> dohxzvo, tsjzvs, kvmjx\n" +
            "tjvry (2251) -> mxxvvt, arugmz, umttqu\n" +
            "cptoly (9)\n" +
            "nvdducc (65)\n" +
            "vommsj (42)\n" +
            "ozacwy (66)\n" +
            "qhbwsiu (67)\n" +
            "dgfqg (93)\n" +
            "pmczhb (68)\n" +
            "axbupt (21)\n" +
            "svxrmhl (79)\n" +
            "wxruhqa (16) -> dqbhi, zykjenf, fyvrp, ylxgn\n" +
            "kvztqrg (73)\n" +
            "kpxdh (32)\n" +
            "cdqpv (14)\n" +
            "lilufvg (192) -> qjool, eiynck\n" +
            "lgbltl (110) -> orlkabb, rguvpi, rgewan\n" +
            "iodna (37)\n" +
            "wevkbue (6)\n" +
            "ucntsl (50) -> iakcl, vlpbqn\n" +
            "pfudc (47) -> wdlpc, eoeac\n" +
            "rewfxf (87)\n" +
            "bgcjcd (62)\n" +
            "uggjwfl (1393) -> kvsnv, ksledpc, lzbzre\n" +
            "hvjal (99)\n" +
            "gfkxqmk (44) -> nrufh, zymmhlb\n" +
            "qnmgp (28)\n" +
            "yzvarwz (68)\n" +
            "yyhsmd (26)\n" +
            "ukzms (21)\n" +
            "ytbmfpn (68)\n" +
            "evgddir (44)\n" +
            "jdnia (43)\n" +
            "fdozub (58) -> ytbmfpn, fprpmof, hqgbfvn, yzvarwz\n" +
            "brrvjn (15)\n" +
            "cwqou (69) -> uajllsw, diwwjq, pvowck\n" +
            "wcfgubc (32)\n" +
            "ffimin (22)\n" +
            "ikxcldw (23)\n" +
            "spqvn (12168) -> vwbwicx, vqanko, cqtuah\n" +
            "escef (184) -> akzwfo, ltweydr\n" +
            "bneqef (141) -> iaxvd, aocbwq\n" +
            "hszuim (2409) -> yrdsxpn, nlehenc\n" +
            "bowxedw (144) -> qdoevfi, epnfp\n" +
            "lrxhito (69)\n" +
            "vwzfk (27)\n" +
            "fsdvpk (48)\n" +
            "oyumlz (90)\n" +
            "zfavz (28)\n" +
            "lrhclhk (56)\n" +
            "godqs (64)\n" +
            "krvtfsj (82)\n" +
            "qmtfv (37)\n" +
            "ygcph (126) -> irsrtbm, krvnq\n" +
            "vaiqef (42)\n" +
            "hrciraz (43)\n" +
            "vkzhxfv (20)\n" +
            "omvflhe (82)\n" +
            "wwtvhb (74)\n" +
            "kloow (11)\n" +
            "ydxsq (6)\n" +
            "mgboos (385) -> lxeeq, wvyht\n" +
            "iaeemxc (155) -> pnxmraq, aqviuy\n" +
            "cyenkm (1226) -> gkpirl, alvji, orofe\n" +
            "sdhvw (39)\n" +
            "tjqiwyw (50)\n" +
            "bphpr (25)\n" +
            "qwqwp (160) -> dvsntva, qmtfv\n" +
            "ehyzqx (70) -> havmcsf, xhzdo\n" +
            "ygzvz (63)\n" +
            "qqesw (14)\n" +
            "llpifh (45)\n" +
            "konmvry (135) -> kwonpn, mklrsr\n" +
            "vwledx (142)\n" +
            "showzx (51)\n" +
            "iefjz (6)\n" +
            "gvajepy (7)\n" +
            "svpwz (44)\n" +
            "oitkdg (35)\n" +
            "wpgdv (168) -> wcfgubc, akwdkl, skvcg\n" +
            "mxxvvt (69) -> rxmxfno, hjibopk\n" +
            "movvd (66)\n" +
            "ydndqy (98)\n" +
            "uelcs (71)\n" +
            "glcbz (87)\n" +
            "pvqmab (78)\n" +
            "nezlnj (90)\n" +
            "zzjgcli (85)\n" +
            "vmdwgiy (45) -> itpjbr, mxtcb\n" +
            "hrrxs (21)\n" +
            "atgrdn (90) -> hfhqvb, cprxbbf, eqrmhiv, rzhwl, mzbvwk, bncbco\n" +
            "rruyk (14)\n" +
            "nquecpp (32)\n" +
            "mvjii (41) -> rffho, rjuizk, dbqujc, nadgwb\n" +
            "tmejjp (45) -> rvgcgd, leccfrx, pyfps, dnqxg, uxpirup, mvice, kwikqd\n" +
            "oxpur (53)\n" +
            "tkjff (96)\n" +
            "yqigck (264)\n" +
            "oriskzl (20) -> jyffs, jncodb, zzjgcli\n" +
            "bmckl (78)\n" +
            "rlfno (208) -> okian, cptoly, vzhrt, akbciz\n" +
            "jasamk (96)\n" +
            "lqnskaj (84)\n" +
            "tgxpafk (107) -> hivkyyy, vdxdq\n" +
            "whpjefc (231) -> oglqg, jpyaa, lycks\n" +
            "ezilows (80) -> osaofz, bdnrzjl\n" +
            "eywfs (75)\n" +
            "vrbpoms (35) -> nezlnj, pysmz, wctwj, oyumlz\n" +
            "nblsqm (43)\n" +
            "efkixrq (10)\n" +
            "pyaco (84)\n" +
            "phzap (70) -> xnjod, bydub, lgpsy\n" +
            "xhidor (64)\n" +
            "fphhjt (32)\n" +
            "qlbapm (6)\n" +
            "xqyyotm (58)\n" +
            "sxlkxs (314) -> vclbj, tjqiwyw\n" +
            "wyujf (15)\n" +
            "gabcawj (159) -> gxlcwkl, vnrwmwp\n" +
            "csmgexg (89)\n" +
            "suakgk (72)\n" +
            "xcfvrz (226) -> vcjhxmu, ikefepo\n" +
            "xppxn (191) -> btsvt, duckgf, hnfjb, hjwug\n" +
            "mgetjw (57)\n" +
            "togddow (53)\n" +
            "unjxp (10)\n" +
            "hkcuo (89)\n" +
            "irsrtbm (45)\n" +
            "mtypps (48)\n" +
            "ayuwm (214)\n" +
            "rgzrfak (52)\n" +
            "epaztgn (74)\n" +
            "myytdk (98)\n" +
            "enbtzt (62) -> xxrcw, teuku\n" +
            "vgqxpp (38)\n" +
            "qfmofym (25)\n" +
            "chihy (108) -> mxbid, iayol, ceibzne\n" +
            "lratbc (73)\n" +
            "zafhcbb (865) -> rlfno, xpgzx, hwssny\n" +
            "afejk (23)\n" +
            "ftdkcfr (163)\n" +
            "ocxbu (6)\n" +
            "vwbwicx (285) -> yjsminx, wfnzaoj, aaavm, ftdkcfr, itfiqj, xzmnjz\n" +
            "fwoavaz (27)\n" +
            "mwmguqn (19)\n" +
            "gwdrk (72)\n" +
            "jfakaii (209) -> azxmib, dmayk\n" +
            "zjlqjvv (91)\n" +
            "fdetm (89)\n" +
            "bncbco (1876) -> fdbnn, fiqgcz, tcyqi\n" +
            "zjthm (59)\n" +
            "beqyys (57)\n" +
            "lagoq (8)\n" +
            "ftimasv (1482) -> ibszdcc, mohlj, srsuyk, zprakvq, xppxn, mswjlev, tuytxy\n" +
            "ytxlw (51)\n" +
            "knpefcs (68)\n" +
            "mozuwfi (23)\n" +
            "nhttnio (78)\n" +
            "zrrklpo (345)\n" +
            "zcszxf (368) -> fmnil, mjoctb\n" +
            "rqbld (67)\n" +
            "rrohj (49)\n" +
            "zxmpvm (67)\n" +
            "gzsltcw (33)\n" +
            "swoxpas (49)\n" +
            "mpkijdc (47)\n" +
            "reyaoo (185) -> dpahgkv, uqcjwp\n" +
            "odvasfk (26)\n" +
            "xfvxqr (27) -> bmckl, nexgspx\n" +
            "llpllm (63)\n" +
            "wimpgm (31)\n" +
            "ocstme (1659) -> terrhqm, jdpncg, jlpfu\n" +
            "tkaax (7058) -> msajn, czsnue, kfgafaq\n" +
            "ngbto (45)\n" +
            "sfopk (65)\n" +
            "pklfpox (781) -> tnuvu, irtey, inggl, nlztkoh, vrbpoms\n" +
            "navzdpq (85)\n" +
            "wohmy (46)\n" +
            "fumcav (205)\n" +
            "buhoxvf (184) -> jcalbf, anendx\n" +
            "irtey (395)\n" +
            "vpdbtl (62) -> dgebx, nmber, iodna\n" +
            "uyflsu (87)\n" +
            "fprpmof (68)\n" +
            "bzeyrs (80)\n" +
            "fgfhom (22) -> gbjzyvh, fgxaf, tvfhovv\n" +
            "rthyaa (65)\n" +
            "nttyljj (74)\n" +
            "hdpqtg (10989) -> nzbmqoj, ppxmvic, ngzrfo\n" +
            "eaemp (7) -> rdssp, evgddir, sdfdasw\n" +
            "uhjzmx (21)\n" +
            "tswgpqg (66)\n" +
            "cajloqp (327) -> milnj, dsycw\n" +
            "vexiqv (2359) -> ncsglzf, gwfkzhk, ozmoirq\n" +
            "eoyva (59)\n" +
            "vuxcbe (17) -> tznvgv, yiqiioh\n" +
            "sgmtal (87)\n" +
            "ghkyui (88)\n" +
            "rwelv (86)\n" +
            "uoezmkf (66) -> kiismq, rycova, nozqe\n" +
            "yjsminx (19) -> txbkss, psisfe\n" +
            "ngbnryz (85) -> okvaid, djblaef\n" +
            "tcksxcj (12)\n" +
            "ksuydd (20)\n" +
            "ztngdq (74)\n" +
            "bnbvm (44)\n" +
            "cbwrev (15)\n" +
            "rwmqvs (74)\n" +
            "glwvlc (74) -> wqbwqi, hjjfc\n" +
            "sdvzj (12)\n" +
            "fiqgcz (217)\n" +
            "wobzkg (99)\n" +
            "wtpgz (17)\n" +
            "tkxots (9)\n" +
            "vvbijk (212) -> eznjfl, zjthm\n" +
            "cagiues (101) -> ffakxgn, derfr\n" +
            "vczbfs (8)\n" +
            "pxgfxbt (19)\n" +
            "zrovx (52) -> nvdducc, sfopk\n" +
            "xtojoy (70)\n" +
            "svypeua (94) -> dpcpy, phxuazm, txdyn\n" +
            "xmhokd (86)\n" +
            "vftol (62)\n" +
            "magml (150) -> pyklnj, gfdyw\n" +
            "anqaz (72)\n" +
            "mwtqk (125) -> lcuhsxu, mnepu\n" +
            "zgjabut (9)\n" +
            "mfngva (6)\n" +
            "uiges (14517) -> tetbx, hhexmn, ytgqzq, dakenrk, uwscgd, yyauqx\n" +
            "lchstmy (319) -> hhtpv, fgjcea\n" +
            "lxyirze (38)\n" +
            "znqux (302) -> rocyko, guaqgw, operlbg, yzmeb, ukedg, vlxqvi, mveyx\n" +
            "rpnlu (34)\n" +
            "sifvsqc (56)\n" +
            "jatvuw (38)\n" +
            "jtwfdu (3727) -> pavabvy, lqorouz, wbydzcm, bqggi, vtzgyeg\n" +
            "pnejfr (122) -> humrsd, fxrtldx\n" +
            "kgxmev (215) -> rthyaa, zvzzst\n" +
            "iqvvnfr (40)\n" +
            "cfcdlh (96088) -> dwtgak, whuak, rqjpza, qpdufu\n" +
            "zbbyckg (14)\n" +
            "ezzzomx (73)\n" +
            "dgzcgr (70)\n" +
            "kvfwsoc (68)\n" +
            "xvbro (62)\n" +
            "hkqulxp (81)\n" +
            "xinyot (75) -> hdtplwx, tzehi, aeylapx, zcwclwc, dobuzav, prfyjj, kuycibx\n" +
            "dxtne (44)\n" +
            "yceabf (14) -> hnoakdg, zcegp, myytdk, ewfpgoj\n" +
            "mohlj (185) -> ockjo, fbfqvq\n" +
            "aqbybws (315) -> ydxsq, tekqjru\n" +
            "kptbzl (41)\n" +
            "gyxopp (61)\n" +
            "smttd (28)\n" +
            "yaeee (43)\n" +
            "bdmmi (61)\n" +
            "eedcta (45)\n" +
            "wmjxypj (151) -> ykntwjm, fepdsz\n" +
            "orlkabb (55)\n" +
            "qppggd (171) -> oxcth, enbtzt, ntidj, hmibku\n" +
            "gfrdgnq (135) -> aiqug, eelcez\n" +
            "fdgvmi (24)\n" +
            "nddzkn (271) -> hoiju, hroegp\n" +
            "ijhzbaa (14)\n" +
            "fqfdw (87)\n" +
            "uoujbtv (32) -> bowxedw, eberpkh, zqignj, qzhgmb\n" +
            "brsgom (111) -> hatujsc, cmuclkp\n" +
            "igjmm (132) -> qndtc, vjmhzau, nblsqm, fdwlwr\n" +
            "cibabc (181) -> neihdi, yaqlguh, cjhxpn, obxki\n" +
            "duckgf (13)\n" +
            "jgdqth (89)\n" +
            "ganlaq (1168) -> tuuqm, kwiiccw, ydnsh\n" +
            "ahaan (23)\n" +
            "cnvce (88)\n" +
            "ccaeaw (98)\n" +
            "cltigxi (48)\n" +
            "rduns (60) -> gfhrh, sxonkbq\n" +
            "gkbmdh (24)\n" +
            "ghbhqnl (74)\n" +
            "dqbhi (78)\n" +
            "tmbuahz (93)\n" +
            "jektqvn (6)\n" +
            "isxqmc (34)\n" +
            "hohmn (89)\n" +
            "bdnrzjl (31)\n" +
            "fwssgyj (126) -> ncdnc, isxqmc\n" +
            "qdnmni (482) -> sjvdfk, gabcawj, qxvpaqr, gfrdgnq, joutwz, jfakaii, cvmlcyx\n" +
            "qpkxs (20)\n" +
            "qygtd (6)\n" +
            "evofre (192) -> cbjyf, eifitp\n" +
            "wpwzlpm (98)\n" +
            "njhtc (80) -> gozszri, exqxh, gkctmh\n" +
            "hlnram (31) -> cdcdck, wnjdsq, nfpawkr, vybjgy\n" +
            "wctwj (90)\n" +
            "gvjuwwn (244) -> evkyf, dvispjs\n" +
            "fcfujgr (82)\n" +
            "mveyx (190) -> hjhaae, bnbvm, gsxxi\n" +
            "kvopnl (99) -> nddzkn, lcxeysi, mvjii, fagsd, sylbpy, uriyyc\n" +
            "wemcfwy (38)\n" +
            "azxmib (9)\n" +
            "qjvtm (82986) -> myfhxk, boropxd, jixdvf\n" +
            "mrucj (211)\n" +
            "dsycw (9)\n" +
            "mjjvkh (55)\n" +
            "tddqw (11164) -> tzumnfw, ahxse, iixhucd, flywvww, jylvi\n" +
            "nyqht (16) -> qmryr, kljfbe, fqfdw, rewfxf\n" +
            "fpenvio (262) -> gwdrk, ugzxbjw\n" +
            "zykjenf (78)\n" +
            "lgpqna (82)\n" +
            "icojter (76)\n" +
            "vjhja (43)\n" +
            "irxpuis (89) -> rjxxk, jbbpzze\n" +
            "nfgme (81)\n" +
            "pedazr (40)\n" +
            "mxbid (23)\n" +
            "weiik (74)\n" +
            "ixruqck (10)\n" +
            "afiqx (38)\n" +
            "zqignj (136) -> mamhdgl, fesyd\n" +
            "dakenrk (222) -> axstuln, tkxots\n" +
            "nrufh (89)\n" +
            "rjfiqcz (66) -> tcujev, ycmhi, ldehas\n" +
            "kgjmfr (1582) -> ooixr, amzaq, hrdvx\n" +
            "bavhssu (23)\n" +
            "ydwtc (219) -> gxpdbf, otsaip\n" +
            "eelcez (46)\n" +
            "jyffs (85)\n" +
            "xvwkwsk (211)\n" +
            "vbysnql (55)\n" +
            "fpuuwxw (184) -> aowql, ycnqav\n" +
            "fvbygj (419)\n" +
            "qskjee (24)\n" +
            "uxpirup (318) -> bbojh, ghobjvb\n" +
            "fnjqvc (150) -> jlhly, buzsiye\n" +
            "kvsnv (134) -> oaiipqw, wohmy\n" +
            "ejjdfwk (82) -> tkjff, ncfpaw\n" +
            "jpcais (21)\n" +
            "hmibku (44) -> njlxjg, cdblpel\n" +
            "fbmcsl (27)\n" +
            "zcwclwc (289) -> jdvwj, oqixvwe\n" +
            "qosoqk (29)\n" +
            "nnysyj (34)\n" +
            "pkejtl (41)\n" +
            "dplpd (82)\n" +
            "dvoylt (45)\n" +
            "tetbx (120) -> shwph, vuydl, dqxhlmf, yjqdgq\n" +
            "cuupma (96)\n" +
            "qdoevfi (39)\n" +
            "xczaehv (14)\n" +
            "yddiq (96)\n" +
            "neihdi (9)\n" +
            "qebjnbs (25)\n" +
            "vlzoz (32)\n" +
            "psisfe (72)\n" +
            "eppcdr (23)\n" +
            "kazqqf (88)\n" +
            "bqggi (1915) -> ilsvcl, czksrye, chihy\n" +
            "qfjnebk (86) -> hlfvf, qcyua\n" +
            "fvdoc (69)\n" +
            "lhopv (48) -> uuxwz, byxxgzt, fpenvio, pdyjzd, qsetl, rhhlps, yceabf\n" +
            "uwisk (32)\n" +
            "mkisdq (74)\n" +
            "ncfpaw (96)\n" +
            "hldolxr (241)\n" +
            "ounuwsx (78)\n" +
            "slkkm (89)\n" +
            "qzhgmb (222)\n" +
            "vppppj (141) -> gamtq, fbmcsl\n" +
            "yjxmv (8)\n" +
            "uljiu (99)\n" +
            "qmnykar (244) -> aurya, rpnlu\n" +
            "ylxgn (78)\n" +
            "elhde (30)\n" +
            "qljqbt (25)\n" +
            "csyuq (16)\n" +
            "phyop (11)\n" +
            "dbxgdw (46)\n" +
            "dsnwpy (330)\n" +
            "ljcjmur (38)\n" +
            "kskmxw (56)\n" +
            "rjxxk (53)\n" +
            "aaizfj (105) -> qiqvce, pwqisw\n" +
            "jyuvjzz (45)\n" +
            "hzxgz (80)\n" +
            "qcsqz (86)\n" +
            "igjhbnk (19)\n" +
            "eupya (67)\n" +
            "kuycibx (236) -> opkren, muztqt, swoxpas\n" +
            "jvegsv (701) -> wpgdv, xxpnqpc, yqcwdh, brxjw, yqigck\n" +
            "edcrp (141) -> lxyirze, hfqij\n" +
            "cmtpu (54)\n" +
            "iakcl (60)\n" +
            "cdcdck (53)\n" +
            "ujdhpxh (78) -> wxcas, sdhtcgx, vxbas, izllv, hldjtya\n" +
            "osdwzw (330)\n" +
            "mthaau (21)\n" +
            "lczpqwp (111) -> nvvnxlx, qdkcyj\n" +
            "lhurwx (55) -> wrwqsw, bgcjcd\n" +
            "yiqiioh (87)\n" +
            "qpbqzwi (84) -> pkrbgb, hxhkfs\n" +
            "zyneso (51)\n" +
            "dvpjab (76)\n" +
            "otdcfi (24)\n" +
            "jvaqrl (87) -> drenb, vpgxh\n" +
            "sxrjnm (128) -> msrlpql, pmczhb\n" +
            "misjd (92) -> fuaaot, jocdav\n" +
            "phxuazm (62)\n" +
            "txdyn (62)\n" +
            "fkawsxk (84)\n" +
            "pyfps (184) -> csvhlkb, simlgvw\n" +
            "nbonyx (46)\n" +
            "vgwkv (93) -> tuzpg, zzgvh\n" +
            "fxucung (63) -> hkcuo, qvstpyq\n" +
            "operlbg (322)\n" +
            "wymlcoj (205) -> rovaby, hyvhosf, ibfxt\n" +
            "osaofz (31)\n" +
            "pgmctro (45)\n" +
            "kmmng (68)\n" +
            "raaqp (34)\n" +
            "jhrxncw (20) -> jeejn, fylvsj\n" +
            "zfufrfx (21)\n" +
            "gfdyw (29)\n" +
            "texqz (53)\n" +
            "ngbourw (45) -> kmmng, vyiufa\n" +
            "ijhvxea (347)\n" +
            "qpdufu (3326) -> mbmsvws, qwqenwn, dnodp\n" +
            "vclbj (50)\n" +
            "wuwnzr (15)\n" +
            "sokar (62)\n" +
            "gcbrstj (97)\n" +
            "tjrdifv (98) -> sybqi, errqggj\n" +
            "tbpyoxy (5672) -> tfwcyid, szqdnd, lkmcvop\n" +
            "fmele (73)\n" +
            "xhorr (2011) -> egoaw, twbcuh\n" +
            "zwtnf (108) -> zhhhbv, qpkxs\n" +
            "bdlkkll (94)\n" +
            "dtjuno (53)\n" +
            "mlkbqhk (90)\n" +
            "racquh (35)\n" +
            "nvvnxlx (40)\n" +
            "crxpc (153) -> beqyys, empap\n" +
            "xxsxwrm (15) -> eonodgj, eulpqss\n" +
            "ykphap (920) -> ehpzl, jhahcgn, wmjxypj, izkcvii, riuhtpn, xfvxqr\n" +
            "letus (9)\n" +
            "abnzhwd (55)\n" +
            "waznyo (20) -> rrhbmgi, dhltapo, wpwzlpm, kbclwl\n" +
            "hroegp (59)\n" +
            "mapirt (2822) -> djwizu, dbxgdw\n" +
            "ykdkp (25)\n" +
            "tgtwgnl (272) -> hkjbbs, nuuvgid\n" +
            "yzmeb (74) -> bouvy, ziaty, uamdcy, xvbro\n" +
            "utnjrpg (97)\n" +
            "wacijzk (202)\n" +
            "nfexc (71)\n" +
            "snpwaur (82)\n" +
            "fwzkvc (66) -> lrkttbz, dwuakz\n" +
            "lliow (117) -> pbdoak, rwelv\n" +
            "tsjzvs (39) -> grrmgdv, sczzyjo, jauxc, cqhxddr, vnxrh, mrucj, xvwkwsk\n" +
            "ixlerg (852) -> nnysyj, shdht\n" +
            "nnmcrj (35)\n" +
            "xdgbnoj (45)\n" +
            "jyjvxw (9)\n" +
            "czksrye (51) -> uphktul, llpllm\n" +
            "thdermn (50) -> uzasvyd, zgnyiz\n" +
            "uajllsw (43)\n" +
            "xlgffbd (59)\n" +
            "ldehas (66)\n" +
            "ctbubmz (134) -> tebbb, qljqbt\n" +
            "qnovi (140) -> bykcfy, qosoqk\n" +
            "qcaxee (54)\n" +
            "dtgnqs (67)\n" +
            "jqmpf (90)\n" +
            "jlcvhl (6)\n" +
            "xhyph (78)\n" +
            "iyqzno (59) -> jbhoa, dteoa, qemad, mtjcqtg\n" +
            "zkpghh (98)\n" +
            "rsebscc (109) -> clxasb, ozacwy\n" +
            "zrmtgk (62) -> wobzkg, hvjal\n" +
            "vybjgy (53)\n" +
            "uvfhx (84)\n" +
            "yonamnd (58) -> knyhb, byhqi, vfkeogg, zoagmd\n" +
            "jukhdvi (116) -> vkgbz, vktrdf\n" +
            "hujizfz (59)\n" +
            "uokfe (12)\n" +
            "trwyel (41)\n" +
            "tskeqa (75)\n" +
            "ikefepo (46)\n" +
            "qumhu (24)\n" +
            "wtjzsm (62)\n" +
            "muztqt (49)\n" +
            "fqpfnc (876) -> wymlcoj, rhsqn, rpcczq, vhdbfe, novtxck, svypeua\n" +
            "dntft (7)\n" +
            "sdfdasw (44)\n" +
            "iaaws (68)\n" +
            "dqrwhri (178) -> iztdlnf, hhjgskh\n" +
            "ucjde (44)\n" +
            "kiismq (42)\n" +
            "grvkl (99)\n" +
            "avrfz (57)\n" +
            "byhqi (58)\n" +
            "oykcbpj (298) -> acwtk, mxogohe\n" +
            "njlxjg (97)\n" +
            "pdxgf (236) -> khumo, qlxowz\n" +
            "havmcsf (98)\n" +
            "ceixcl (12)\n" +
            "wurjtrm (33)\n" +
            "mkdxj (97)\n" +
            "ygoxtx (91)\n" +
            "fmnil (16)\n" +
            "nvbococ (118) -> bkhnhga, nmlmm\n" +
            "upstldy (21)\n" +
            "iojycd (158) -> frmmub, mjjvkh, xeblqw, wrrdy\n" +
            "nowyljq (184) -> getbk, eveoo\n" +
            "oqqnazv (71) -> pjrtj, godqs\n" +
            "tflcc (201) -> fmzpc, upstldy\n" +
            "bkcozk (66)\n" +
            "osfsey (25) -> ueikvt, ndpwr\n" +
            "whszfc (135) -> illzp, droaqx\n" +
            "npiost (58)\n" +
            "pyfyx (25)\n" +
            "ftomb (69)\n" +
            "irxbhi (96)\n" +
            "espkomt (97)\n" +
            "acwtk (25)\n" +
            "qmwbv (157) -> tcksxcj, ccxsuk\n" +
            "rgewan (55)\n" +
            "mwbzjrn (72)\n" +
            "kcawhph (23)\n" +
            "ukedg (262) -> elhde, aeeyxh\n" +
            "xtckrp (36) -> eelwu, kwwhiv, brzrx\n" +
            "lcobq (109) -> yafph, bczzzw\n" +
            "mfhtd (89) -> vpqmdqf, btxgac\n" +
            "qqdrox (14)\n" +
            "mxrvwib (36)\n" +
            "nxlzxzq (37)\n" +
            "yukbvwy (89)\n" +
            "bhfkje (15)\n" +
            "arhfcx (99)\n" +
            "sybqi (55)\n" +
            "xsdlmg (59)\n" +
            "lyqbnl (47)\n" +
            "slaiad (11)\n" +
            "qignfgc (99)\n" +
            "iuuhcc (54)\n" +
            "kljfbe (87)\n" +
            "itpjbr (98)\n" +
            "fxrtldx (35)\n" +
            "hhxofiu (80) -> tmejjp, srqipi, jpsoy\n" +
            "qoxcvs (12)\n" +
            "iayol (23)\n" +
            "vtzgyeg (59) -> ingzgv, khaxl, cejlti, iuuck, alsrt, fpwfhqa, sabkxwy\n" +
            "rbhzdm (40)\n" +
            "wuezook (82)\n" +
            "qjjjm (60)\n" +
            "marrb (15)\n" +
            "pyklnj (29)\n" +
            "hgqsp (99)\n" +
            "jiorqgn (136) -> dbdne, pnejfr, qfjnebk, uoezmkf, dqrwhri\n" +
            "duruha (25)\n" +
            "fisyh (46) -> dpygp, nglwlyl\n" +
            "dpqsg (290)\n" +
            "byxxgzt (386) -> nyghbkz, unjxp\n" +
            "omzvw (83) -> owaqfb, betkfui, xklmcmc, avemla\n" +
            "juaekax (21)\n" +
            "bczzzw (66)\n" +
            "dmayk (9)\n" +
            "jdpncg (90) -> oqpxd, sifvsqc, rrdin\n" +
            "iaxvd (51)\n" +
            "uriyyc (261) -> vlzoz, worqvps, flcyx, lzfuzm\n" +
            "agpuxcs (17)\n" +
            "flays (52) -> uyflsu, awhgs, apwqbdt, vmcdxsc\n" +
            "aijyga (49)\n" +
            "paetkc (854) -> pmttpt, ihvtb, ucntsl\n" +
            "zvzzst (65)\n" +
            "untiezr (6) -> erzpzd, movvd, ecrkcs\n" +
            "ndflhsj (1709) -> azzpq, ngbourw, ypccy, jmpyaib\n" +
            "asiigv (25) -> fbebcq, lchstmy, ciwydtl\n" +
            "wcmif (86)\n" +
            "bydub (73)\n" +
            "terrhqm (242) -> ydpxkx, pdebsm\n" +
            "dzdgx (35)\n" +
            "empap (57)\n" +
            "wuavkqo (68) -> davnh, mukkakg\n" +
            "pfbukuq (33)\n" +
            "zeien (79)\n" +
            "blntgdj (35)\n" +
            "rrdin (56)\n" +
            "aycnip (42) -> iyhuvj, kunzaag, ftimasv, zxcjlk, mlvpmj\n" +
            "oipjur (178) -> cipzed, ijhzbaa\n" +
            "efvwgf (41)\n" +
            "ctjyra (89)\n" +
            "jstihz (93) -> ulymdk, uiqzhqu, vhzoa\n" +
            "ehwofnh (18) -> kvopnl, kaihb, ocstme, hszuim, mjnjzet, dhhcmdj, ndflhsj\n" +
            "nlehenc (12)\n" +
            "dsoipkq (93)\n" +
            "qlxowz (37)\n" +
            "fppwvpe (84)\n" +
            "tmzvfia (93)\n" +
            "ufryj (412)\n" +
            "nzbmqoj (416) -> fjgns, igbiz, iqefm\n" +
            "kgclit (49)\n" +
            "bjtca (22)\n" +
            "xxrcw (88)\n" +
            "sclfvp (7741) -> psmlok, kgjmfr, grqfud\n" +
            "cjwnq (191) -> uihbo, lqnskaj\n" +
            "qubxyhi (68)\n" +
            "fhpik (42)\n" +
            "vaxierg (63)\n" +
            "ayrmwy (77)\n" +
            "qkshp (85)\n" +
            "feope (63)\n" +
            "zymmhlb (89)\n" +
            "hdfugh (44)\n" +
            "gsxxi (44)\n" +
            "alvji (226) -> pxgfxbt, klflx\n" +
            "tillos (262) -> zxqbpy, nntot\n" +
            "ngwdz (96)\n" +
            "xjdxfb (82)\n" +
            "ncsglzf (151) -> tsasgm, phltmve\n" +
            "anlopt (139) -> apbzweg, zcxsxky\n" +
            "nfpfpq (97)\n" +
            "zgnyiz (66)\n" +
            "flspx (15)\n" +
            "pofye (42)\n" +
            "xftjd (93) -> racbcat, zefywno\n" +
            "rukmtyd (192) -> wfaus, zeipd\n" +
            "fzivpz (64) -> espkomt, nfpfpq, mjkgsg, mkdxj\n" +
            "dmbdxs (78)\n" +
            "uaofl (27)\n" +
            "kdbbkg (22)\n" +
            "owbhv (151) -> klaqkg, qskjee\n" +
            "uajkvy (212) -> odpuf, wyujf, pprsdm\n" +
            "kikni (59)\n" +
            "myfkdkl (17)\n" +
            "dttxe (24)\n" +
            "iwejes (8)\n" +
            "qjamq (52)\n" +
            "ypccy (39) -> itimbmg, ekzro\n" +
            "djdiyl (51) -> zyxswnx, maykdnd\n" +
            "jalyzrg (189) -> socgh, nqdab\n" +
            "plexk (87)\n" +
            "eberpkh (62) -> bzeyrs, ahotpd\n" +
            "mwnczjf (46)\n" +
            "ibszdcc (151) -> bavhssu, ikxcldw, ahaan, afejk\n" +
            "iipyba (25)\n" +
            "bubcg (42)\n" +
            "vdlhr (88)\n" +
            "bkjpa (626) -> zbtgzh, jalyzrg, ppmjk, lliow, ydwtc\n" +
            "qerhwfp (122) -> vxbieux, wcmif, aktlne\n" +
            "skvcg (32)\n" +
            "tnjkbcd (51)\n" +
            "genete (23)\n" +
            "ndpwr (87)\n" +
            "aurya (34)\n" +
            "dqmcksd (64)\n" +
            "cjikbu (6)\n" +
            "eifitp (15)\n" +
            "aeylapx (383)\n" +
            "errqggj (55)\n" +
            "lpflvwn (296) -> iqvvnfr, pedazr\n" +
            "tvgfdb (88) -> kcawhph, genete\n" +
            "jpjbtm (96)\n" +
            "dujlhd (83)\n" +
            "ilsvcl (95) -> dqabk, efvwgf\n" +
            "novtxck (234) -> oijissd, mozuwfi\n" +
            "gxpdbf (35)\n" +
            "pxkxjy (20)\n" +
            "fylvsj (82)\n" +
            "cyxvk (81)\n" +
            "uhvtx (5)\n" +
            "tziitox (41) -> mtlutxf, lukls\n" +
            "jdvwj (47)\n" +
            "wdlpc (97)\n" +
            "xriycgo (121) -> fkuvh, showzx\n" +
            "dnodp (148) -> rxcyz, qfdlr, xavay, srofdd\n" +
            "saflt (60)\n" +
            "kmjbuf (51) -> kebxaum, dfmrhz\n" +
            "pbdoak (86)\n" +
            "qszzvq (15)\n" +
            "twdlx (88)\n" +
            "pduiy (1736) -> dgfjw, xqyyotm\n" +
            "frmmub (55)\n" +
            "mlvpmj (2598) -> vppppj, jpyfzy, irxpuis\n" +
            "diiqyx (26)\n" +
            "xuknyi (273) -> gvotglo, dnaclwb\n" +
            "tuzpg (13)\n" +
            "hhexmn (225) -> hcvfmh, aywgi, bcyusmh\n" +
            "qxvfq (204)\n" +
            "jncodb (85)\n" +
            "uihbo (84)\n" +
            "islbpsv (82)\n" +
            "omqxa (81)\n" +
            "aaavm (163)\n" +
            "ecblhee (55)\n" +
            "zrtrhph (6535) -> czwrn, jaiqco, tppwhd, wcgfkq, ynsjo, vhysl, nuwaia\n" +
            "ckpsa (84)\n" +
            "ykoyzh (12) -> sqdyn, ghbmnod\n" +
            "rycova (42)\n" +
            "izgkl (32)\n" +
            "fozofs (47)\n" +
            "dnrle (15)\n" +
            "hkqdsjx (360) -> wpzcj, tnrej\n" +
            "elqgxm (1472) -> thdermn, fnjqvc, zrovx\n" +
            "hcvfmh (5)\n" +
            "bykcfy (29)\n" +
            "hvqex (45)\n" +
            "grrmgdv (151) -> clbkb, pzbnrp, zaccgw, dnrle\n" +
            "haphukg (62)\n" +
            "jjrcs (45)\n" +
            "vmnys (100) -> npiost, shwsc, hqesnp\n" +
            "ghobjvb (21)\n" +
            "pvesfah (202)\n" +
            "hmdvz (38)\n" +
            "hjjfc (66)\n" +
            "cqgdvfr (13) -> kpgzhfa, uembj\n" +
            "igbiz (77) -> qcsqz, qtmszjy, hqqcmi\n" +
            "pafhmi (41)\n" +
            "brzrx (379) -> yjxmv, szwdc, iwejes, xrbipx\n" +
            "kfgafaq (95) -> anqaz, mwbzjrn\n" +
            "jxmvpr (8)\n" +
            "xklmcmc (84)\n" +
            "ykrdbmw (66) -> ptvuev, juahc, mnrsu, eqzjl, oseerig, ufcaif\n" +
            "jhahcgn (19) -> nzorq, sipdbpd\n" +
            "xksfmz (1213) -> wacijzk, pvesfah, anobpj, vounq\n" +
            "qxcds (27) -> qmnjlw, hvche\n" +
            "durpmst (28)\n" +
            "krtdc (266)\n" +
            "dpcpy (62)\n" +
            "sfdtqc (63) -> jqmpf, mlkbqhk\n" +
            "vnbuj (20)\n" +
            "opath (91) -> fdozub, dsnwpy, vvbijk, osdwzw, rxmvx, icmfzt\n" +
            "vhvmcal (6)\n" +
            "kpbdkq (260) -> lczyx, ncffowp\n" +
            "yyauqx (80) -> lislxi, rotbjjy\n" +
            "rguvpi (55)\n" +
            "iyhuvj (19) -> nopmvrz, fzivpz, nzskcw, pkhhbp, edpkt, eodye, lyxaz\n" +
            "zwtznsv (49) -> fwwvg, twdlx, skhjby, kfrar\n" +
            "shdht (34)\n" +
            "qoouyiw (1117) -> wwxctxk, suslpgz, rduns\n" +
            "jvhnuoq (44)\n" +
            "bouvy (62)\n" +
            "olvnqyu (23) -> kuwuy, gcbrstj\n" +
            "nakiug (78)\n" +
            "ajqaniw (48) -> uzowuwt, dxtne\n" +
            "mzbvwk (1081) -> dynfb, qydug, rsebscc, fxucung, hldolxr, jbutmk\n" +
            "axouzs (199) -> urvwum, blafijn, ytacid, ysbjlf, qmnykar, vebuhzt\n" +
            "albxmd (89)\n" +
            "slvkou (42) -> xcdycx, dplpd\n" +
            "pzbnrp (15)\n" +
            "vvyhqon (8)\n" +
            "pqrcl (171) -> ojdlcls, dmbdxs\n" +
            "lnuve (840) -> zjowzz, cllhod, uzzuddz, agzideh, mzfmw, chzno\n" +
            "ukzcbat (884) -> rxhmtg, iojycd, mpjvq\n" +
            "pmttpt (140) -> wuwnzr, bhfkje\n" +
            "mvlvwo (66) -> yogzh, xstglba, zwtznsv\n" +
            "yafph (66)\n" +
            "uhtmhy (84)\n" +
            "wnjdsq (53)\n" +
            "evliz (151) -> nxtwjc, agpuxcs\n" +
            "pdawj (39) -> ytxlw, ozackw\n" +
            "ftntagd (27) -> qkshp, zwxkyn\n" +
            "flywvww (598) -> cqgdvfr, dhugaw, whszfc\n" +
            "vhjri (92)\n" +
            "jnenzeu (171) -> wqtcnyz, krfwuk\n" +
            "zjowzz (67) -> ebcls, lratbc, kvztqrg\n" +
            "jgvsp (52) -> jeqlbxq, kzjdhi\n" +
            "ohpvb (69)\n" +
            "seztb (7)\n" +
            "oqixvwe (47)\n" +
            "mgvovnb (127) -> blntgdj, dzdgx\n" +
            "eyfds (88)\n" +
            "lkmcvop (35) -> zpqlfyn, twgajx, yafxw\n" +
            "isesfdw (48)\n" +
            "ywsjwhi (80)\n" +
            "mklrsr (54)\n" +
            "kxotrd (61) -> sjyymi, wrtarf\n" +
            "pavbfb (14)\n" +
            "serls (40)\n" +
            "gpkgkv (59) -> aqbybws, zzlgja, ajjyaog, qkgtzy, pqrcl, gimjz\n" +
            "ftnzxce (31)\n" +
            "qxvpaqr (183) -> dhgfms, gjbeou, phyop, sfevya\n" +
            "edpkt (368) -> ohgyus, pofye\n" +
            "joutwz (215) -> wxeki, jlcvhl\n" +
            "zvldmx (22)\n" +
            "vmcdxsc (87)\n" +
            "fvkqehj (37)\n" +
            "jjkyj (14)\n" +
            "fcakejv (4140) -> ykrdbmw, jzklwrz, kjfmxih, wlppp\n" +
            "kaihb (1131) -> kxotrd, qxcds, cagiues, edcrp, olvnqyu, cibabc\n" +
            "tcyqi (77) -> xtojoy, adkszk\n" +
            "whuak (62) -> idqfawa, ganlaq, odqkxi\n" +
            "nzorq (82)\n" +
            "jyjkwnc (49)\n" +
            "lczyx (77)\n" +
            "gynwzem (92) -> uhtmhy, yixmif, fppwvpe\n" +
            "dptzp (75)\n" +
            "erlwita (34)\n" +
            "gekwq (77)\n" +
            "vbrwu (51)\n" +
            "pqrvq (41)\n" +
            "fdbnn (61) -> pvqmab, nhttnio\n" +
            "mcihl (24)\n" +
            "ncmsneu (344)\n" +
            "fpqfvd (79)\n" +
            "vogdr (45)\n" +
            "ncffowp (77)\n" +
            "ushmwu (97) -> niiwne, bdudb\n" +
            "fmdxpvz (24)\n" +
            "vbukg (15) -> npjbfxb, vyfkoc, invkz\n" +
            "wlfzzwr (62)\n" +
            "mjoctb (16)\n" +
            "ipwdq (76)\n" +
            "hwssny (78) -> vpvvmqv, dujlhd\n" +
            "zfjuv (88)\n" +
            "qbepv (67)\n" +
            "xrbipx (8)\n" +
            "uafzt (78)\n" +
            "pwhdea (96)\n" +
            "izxkgl (81)\n" +
            "biner (121) -> qvvgle, aptrqbk, nmcxmr\n" +
            "szqdnd (284) -> xxsxwrm, eaemp, alpoep\n" +
            "ihvtb (130) -> pxkxjy, vnbuj\n" +
            "wjpvpg (53) -> tfqel, wfnrq, mgboos\n" +
            "pysmz (90)\n" +
            "stqsnlz (89) -> odvasfk, eieqwo\n" +
            "dtbgg (26)\n" +
            "zermehr (66) -> buwffdx, icojter\n" +
            "sefzkr (118) -> igfvkh, upvzg\n" +
            "vpvvmqv (83)\n" +
            "ufyxgmv (73) -> xjkzvpn, tvzbk\n" +
            "nlztkoh (383) -> jektqvn, cjikbu\n" +
            "vhysl (346) -> ezcmxxy, phzap, clfvfbd, reyaoo\n" +
            "fkcwv (71)\n" +
            "vhdbfe (214) -> wurjtrm, pfbukuq\n" +
            "ziaty (62)\n" +
            "rrhiz (113) -> pxknh, htxwgxj\n" +
            "aeeyxh (30)\n" +
            "ddiqs (89)\n" +
            "ugyebr (82) -> mgetjw, eecdh, fcxag, muburl\n" +
            "hobrps (112) -> epaztgn, mkisdq\n" +
            "tvlhxbd (94)\n" +
            "edshwfr (50)\n" +
            "qwtss (91) -> duruha, qebjnbs\n" +
            "horkr (38)\n" +
            "vsyhi (35)\n" +
            "klaqkg (24)\n" +
            "wirzgw (12)\n" +
            "oglqg (6)\n" +
            "uxjzpl (1247) -> zwtuhb, jvaqrl, uajkvy\n" +
            "qdoici (98)\n" +
            "gvotglo (12)\n" +
            "myaznzp (162) -> ffzpfp, xhidor\n" +
            "fdwlwr (43)\n" +
            "cqhxddr (211)\n" +
            "pmhmqfc (89)\n" +
            "zcxsxky (21)\n" +
            "nqdab (50)\n" +
            "emyiqu (67)\n" +
            "vyfkoc (94)\n" +
            "ohkkn (32)\n" +
            "zqnrzvk (15)\n" +
            "jpadrxu (29)\n" +
            "mxtcb (98)\n" +
            "ylysm (1333) -> pmosxof, njhtc, vpdbtl\n" +
            "ennlusa (138) -> eedcta, mzmyto, pgmctro, eakfc\n" +
            "sbovaf (84)\n" +
            "mwikxz (21)\n" +
            "hxhkfs (88)\n" +
            "gcjdpx (8) -> dntxe, grvkl\n" +
            "cpnwxr (34)\n" +
            "sylbpy (355) -> omtrskm, mhzpj\n" +
            "rhsqn (204) -> afiqx, hmdvz\n" +
            "umttqu (221)\n" +
            "gozszri (31)\n" +
            "leaohjx (84)\n" +
            "vcjhxmu (46)\n" +
            "zyybpa (50)\n" +
            "iumspgx (1713) -> fqpfnc, lnuve, oyrmwz, zcptsvh, znqux, grgkrf\n" +
            "yaueae (352) -> sdvzj, wfnrlgo\n" +
            "tpnnkuw (190) -> nxlzxzq, fvkqehj\n" +
            "jntictz (131) -> ifengok, vkzhxfv, tsdgx\n" +
            "ewfpgoj (98)\n" +
            "qasbblg (55)\n" +
            "dbdne (39) -> zyneso, svxpof, mtwbsrl\n" +
            "cwwwj (201) -> pdxgf, ugyebr, tillos\n" +
            "grksx (23)\n" +
            "kemdk (6)\n" +
            "ysbjlf (144) -> pyaco, leaohjx\n" +
            "nlbvsaj (47)\n" +
            "eyovcb (195) -> lhurwx, qwqzr, tpcmp, ngffuwc, nmdit, xfwju\n" +
            "uwscgd (48) -> bjkfl, jpjbtm\n" +
            "ojsntix (6025) -> xinyot, pklfpox, suzkkr, fhifg\n" +
            "eluqn (226) -> kikni, ytvsrny\n" +
            "bbojh (21)\n" +
            "dhgfms (11)\n" +
            "mtwbsrl (51)\n" +
            "mgene (12150) -> xtckrp, mvlvwo, eyovcb\n" +
            "iuuck (93) -> gwkdpj, ximfci, wlfzzwr, mopos\n" +
            "teuhjl (14)\n" +
            "orofe (52) -> oxpur, ullgsq, texqz, bssnhty\n" +
            "tppwhd (46) -> zpxbc, buhoxvf, nyqht, asgmujz\n" +
            "hnoakdg (98)\n" +
            "dqxhlmf (30)\n" +
            "luxtn (73)\n" +
            "kkzzctu (12) -> ygoxtx, zjlqjvv\n" +
            "eoeac (97)\n" +
            "qwqzr (179)\n" +
            "ydapn (41)\n" +
            "zzgvh (13)\n" +
            "iebfp (86)\n" +
            "cvmgc (98)\n" +
            "cllhod (286)\n" +
            "vlpbqn (60)\n" +
            "qnbxo (206) -> nxfjeuy, teafht\n" +
            "rjuizk (87)\n" +
            "twbcuh (5)\n" +
            "kavzymu (248) -> vgfve, letus\n" +
            "ohgok (98)\n" +
            "qfdlr (72) -> zyybpa, zyqsny\n" +
            "wlppp (2421) -> jiybk, ctebhmp, vgwkv\n" +
            "qkgtzy (204) -> trwyel, kptbzl, fxaom\n" +
            "sdjshz (55)\n" +
            "fzrfow (32) -> dntzv, qaosty\n" +
            "vmlmrk (109) -> zpdcajt, hdfugh\n" +
            "skhjby (88)\n" +
            "fgxaf (20)\n" +
            "ngzrfo (431) -> pbebtfc, ndgra, texjaoa, cwqou, qnovi\n" +
            "mbmsvws (662) -> yhmclb, glcbz\n" +
            "nglwlyl (84)\n" +
            "gamtq (27)\n" +
            "jtxvw (80) -> dgfqg, tcymb, rtjax\n" +
            "yjffdl (22)\n" +
            "xecjyv (74)\n" +
            "dpygp (84)\n" +
            "mqhwy (74)\n" +
            "bkhnhga (97)\n" +
            "yplyevo (75)\n" +
            "xzxsnus (54)\n" +
            "aktlwea (43)\n" +
            "oyyfhxh (84) -> togddow, dtjuno, twhnzo\n" +
            "xndzibu (40)\n" +
            "hbabeiy (300) -> nfomnyt, kuqmkzc\n" +
            "hoiju (59)\n" +
            "fnbpxoq (646) -> fscnkbm, ygzyhpx, buplvox\n" +
            "okian (9)\n" +
            "kwiiccw (9) -> cyxvk, hkqulxp, nfgme\n" +
            "lyfsi (34)\n" +
            "kpgzhfa (90)\n" +
            "lyxaz (388) -> ohkkn, fphhjt\n" +
            "xcdycx (82)\n" +
            "subedu (96) -> aijyga, hmsfs\n" +
            "yhhzefl (272) -> knnmsai, vhgtr, eolzkkc\n" +
            "shdbfhl (96)\n" +
            "xcaqylz (11)\n" +
            "iahug (397) -> waangt, nqijykz, pnhmvd\n" +
            "xyfclte (55)\n" +
            "knyhb (58)\n" +
            "nntot (24)\n" +
            "iixhucd (13) -> fwssgyj, kkzzctu, fpuuwxw, subedu, ycnfy, vkufxby\n" +
            "eflbhmn (77)\n" +
            "qqkvxv (52)\n" +
            "zaccgw (15)\n" +
            "vqbvnf (31)\n" +
            "agzideh (214) -> eelrlu, mxrvwib\n" +
            "znslgho (67)\n" +
            "amrvw (52)\n" +
            "tcujev (66)\n" +
            "xzmnjz (59) -> diiqyx, pahywto, ucozsnh, zwjuqr\n" +
            "euaku (22)\n" +
            "jqkrk (84)\n" +
            "ytgdbn (22)\n" +
            "buhmvt (24)\n" +
            "ygeeepa (32) -> hywytz, ehyzqx, kavzymu, krtdc\n" +
            "tfaxln (196) -> kloow, vpmyvzz\n" +
            "mtlutxf (78)\n" +
            "wgbupli (63)\n" +
            "pahywto (26)\n" +
            "hmsfs (49)\n" +
            "pavabvy (1241) -> grbnbad, lcobq, sinfhwq, vmdwgiy, xvjmz\n" +
            "tenzlo (12)\n" +
            "apwqbdt (87)\n" +
            "hfqij (38)\n" +
            "aocbwq (51)\n" +
            "kwonpn (54)\n" +
            "gzxpx (52)\n" +
            "vyiufa (68)\n" +
            "rhhlps (318) -> jvhnuoq, cfhbh\n" +
            "zvtsnz (176) -> jpcais, pgllqy\n" +
            "uuxwz (360) -> kjbps, idxgock\n" +
            "nzskcw (400) -> dtbgg, yyhsmd\n" +
            "ufcaif (408) -> ffimin, fohnt\n" +
            "hiotqxu (877) -> ohlhl, lpweyw, fgfhom\n" +
            "qahbhq (151) -> emyiqu, znslgho, dtgnqs, eupya\n" +
            "pdebsm (8)\n" +
            "wfnzaoj (133) -> zqnrzvk, marrb\n" +
            "rxhmtg (244) -> puzsftr, qhbwsiu\n" +
            "vdezdp (68)\n" +
            "krfwuk (63)\n" +
            "rffho (87)\n" +
            "slbun (66)\n" +
            "rybovf (34)\n" +
            "sxonkbq (50)\n" +
            "cqtuah (63) -> flays, hkqdsjx, zcszxf\n" +
            "czmxofi (81)\n" +
            "qjool (41)\n" +
            "lnnhuld (43)\n" +
            "pxknh (36)\n" +
            "dvispjs (15)\n" +
            "shwsc (58)\n" +
            "jaiqco (950) -> ctbubmz, vaale, jhrxncw\n" +
            "zyqsny (50)\n" +
            "btsvt (13)\n" +
            "rfuqci (64) -> xjhfgv, omqxa, obkparr, izxkgl\n" +
            "dfxox (146) -> horkr, wemcfwy, ljcjmur\n" +
            "nmcxmr (20)\n" +
            "kjfmxih (2163) -> nuomen, ufyxgmv, fumcav\n" +
            "ntidj (214) -> qoxcvs, ceixcl\n" +
            "quhyxh (274)\n" +
            "awhgs (87)\n" +
            "horeib (340) -> jcpbsc, pcdpxv\n" +
            "czsnue (183) -> durpmst, olosbi\n" +
            "fgcjopa (52)\n" +
            "lfveq (292) -> jgrigjx, oiyohma, qumhu, dttxe\n" +
            "ahhqrp (55)\n" +
            "rtgpmf (94) -> dvoylt, llpifh, yusdv, hvqex\n" +
            "qxkcxj (46)\n" +
            "gbjzyvh (20)\n" +
            "ksizuia (77)\n" +
            "frvqvlo (92)\n" +
            "okvaid (79)\n" +
            "mxogohe (25)\n" +
            "ullgsq (53)\n" +
            "vnrwmwp (34)\n" +
            "rujdblh (15833) -> ihtcuq, tvhkggh, igjmm, pbonnr\n" +
            "yogzh (305) -> mtypps, pmytxie\n" +
            "xhljlrn (6)\n" +
            "tbhdon (72)\n" +
            "uutzk (77)\n" +
            "tzehi (199) -> swkfz, vhjri\n" +
            "dynfb (131) -> abnzhwd, fxrkpax\n" +
            "cfhbh (44)\n" +
            "zxqbpy (24)\n" +
            "lzbzre (97) -> vjhja, obctcme, yaeee\n" +
            "ppxmvic (45) -> gynwzem, yhhzefl, eluqn, ncmsneu\n" +
            "pnhmvd (242)\n" +
            "dhpoh (5)\n" +
            "wrwqsw (62)\n" +
            "gxgnzi (186) -> lmrhhj, uaofl, yiipi\n" +
            "joryqqt (44)\n" +
            "uvvsl (67) -> xlgffbd, zdbjk\n" +
            "nqijykz (168) -> fmtzdw, jwrcrra\n" +
            "kfrar (88)\n" +
            "rovaby (25)\n" +
            "mkuow (31)\n" +
            "guaqgw (130) -> vjfkl, pwhdea\n" +
            "lehkqty (48)\n" +
            "twgajx (174) -> uokfe, oircg, wirzgw, tenzlo\n" +
            "krvnq (45)\n" +
            "gdpmx (77) -> gachpm, zcohp, pqrvq, pafhmi\n" +
            "hdtplwx (255) -> gahopg, dqmcksd\n" +
            "xszbzi (13) -> sxrjnm, ewowmhn, qjdzqm, tpnnkuw, rjfiqcz, nowyljq\n" +
            "mjryqy (118) -> qjamq, vgtvj\n" +
            "rvgcgd (332) -> kwfghyi, gvajepy, seztb, dntft\n" +
            "npwjod (7625) -> ryxauy, fzrfow, xhpbaer\n" +
            "kwwhiv (263) -> zgltwud, weiik\n" +
            "lrqskol (11)\n" +
            "eonodgj (62)\n" +
            "srqipi (1107) -> uxxwj, tgxpafk, sfdtqc, konmvry, tflcc, ngbnryz\n" +
            "bcyusmh (5)\n" +
            "kwikqd (274) -> jdnia, wdjpqq\n" +
            "saqta (60)\n" +
            "hivkyyy (68)\n" +
            "zeipd (13)\n" +
            "wfnrq (45) -> ccaeaw, qdoici, cvmgc, wpulnl\n" +
            "zzypx (96)\n" +
            "nopmvrz (340) -> rrfyhi, lrhclhk\n" +
            "ezbax (97)\n" +
            "oyrmwz (42) -> fvbygj, omzvw, ieejg, qahbhq, vjaffbt, poywgwi\n" +
            "eznjfl (59)\n" +
            "dmawkm (6)\n" +
            "hclhewh (82)\n" +
            "qcyua (53)\n" +
            "vzylhou (36)\n" +
            "nhylqxq (88)\n" +
            "ueikvt (87)\n" +
            "fkuvh (51)\n" +
            "rrfyhi (56)\n" +
            "btxgac (55)\n" +
            "buwffdx (76)\n" +
            "pwqisw (72)\n" +
            "uzzuddz (146) -> dgzcgr, anprhpf\n" +
            "ygzyhpx (84) -> gzsltcw, fhsxx\n" +
            "jeqlbxq (48)\n" +
            "esroty (22)\n" +
            "zdbjk (59)\n" +
            "lpxcjq (46)\n" +
            "akbciz (9)\n" +
            "njogs (11)\n" +
            "rpcczq (134) -> jnpwbjj, fawadg\n" +
            "ycnfy (194)\n" +
            "ciaql (8)\n" +
            "obctcme (43)\n" +
            "jpyaa (6)\n" +
            "ydnsh (172) -> xndzibu, pcciu\n" +
            "anobpj (152) -> ykdkp, pyfyx\n" +
            "dnqxg (348) -> qlbapm, xhljlrn\n" +
            "fhfcmv (131) -> ctpac, iaaws\n" +
            "ygfyhh (31)\n" +
            "aywwpfz (352) -> bsqbqtw, ygfyhh\n" +
            "wrtarf (78)\n" +
            "oaiipqw (46)\n" +
            "hvyscq (282) -> zagrlc, rrohj\n" +
            "nuwaia (1310) -> zzypx, shdbfhl\n" +
            "kljhkzg (50)\n" +
            "dvnzgo (89)\n" +
            "izkcvii (85) -> jyjkwnc, yxxrv\n" +
            "pgllqy (21)\n" +
            "derfr (58)\n" +
            "qgudtwk (67)\n" +
            "obkparr (81)\n" +
            "gjbeou (11)\n" +
            "zefywno (78)\n" +
            "oxcth (74) -> wuezook, snpwaur\n" +
            "pulfop (8)\n" +
            "tnrej (20)\n" +
            "vkufxby (184) -> ewlryhh, dhpoh\n" +
            "budohp (82)\n" +
            "rotbjjy (80)\n" +
            "ooixr (55) -> pmhmqfc, eevia\n" +
            "avemla (84)\n" +
            "hbjrjw (26) -> dkuds, qtyeiv, hobchva, ltfxsl\n" +
            "nfpawkr (53)\n" +
            "aqcscz (21)\n" +
            "zagrlc (49)\n" +
            "eulpqss (62)\n" +
            "vnxrh (63) -> mqhwy, xecjyv\n" +
            "mopevhs (220) -> cnnajhy, ywsjwhi\n" +
            "tsdpmhr (11)\n" +
            "ggmjft (197)\n" +
            "khaxl (251) -> yzfbc, jjrcs\n" +
            "uchxwm (69)\n" +
            "rtjzffy (41)\n" +
            "rguht (60)\n" +
            "rfmbo (92)\n" +
            "eqrmhiv (55) -> eiceooz, xuxuy, horeib, waznyo, ufryj, zbscr\n" +
            "srsuyk (75) -> ensngra, kskmxw, jyxztzm\n" +
            "iwbzsq (416) -> kemdk, iefjz, vhvmcal, qygtd\n" +
            "ximfci (62)\n" +
            "ycmhi (66)\n" +
            "zddnqld (76)\n" +
            "qemad (52)\n" +
            "teuku (88)\n" +
            "aktlne (86)\n" +
            "uphktul (63)\n" +
            "vxbas (304) -> cyzobwd, juaekax, axbupt, mwikxz\n" +
            "tzhto (297) -> jaxczdp, bphpr\n" +
            "vjmhzau (43)\n" +
            "mvhybn (66)\n" +
            "jbbpzze (53)\n" +
            "davnh (33)\n" +
            "ofshfcn (274)\n" +
            "hhqks (93)\n" +
            "ckatuf (280) -> uokdqce, zhiacn, csyuq\n" +
            "mnjhqkb (57)\n" +
            "vdexe (66)\n" +
            "kgiswh (15)\n" +
            "skxcsro (71)\n" +
            "whbqia (23682) -> uiges, jtwfdu, spqvn, gworrlc, mgene, aycnip\n" +
            "icmfzt (288) -> jldxskr, zbbyckg, teuhjl\n" +
            "xstglba (297) -> qqkvxv, fgcjopa\n" +
            "uamdcy (62)\n" +
            "fesyd (43)\n" +
            "jsdzajv (89)\n" +
            "ohlhl (82)\n" +
            "gwkdpj (62)\n" +
            "hrdvx (191) -> dcdxxv, uhjzmx\n" +
            "wwxctxk (128) -> vczbfs, ciaql, jmnhj, vvyhqon\n" +
            "ctebhmp (119)\n" +
            "zhhhbv (20)\n" +
            "ocspnqg (84) -> kpdms, ucjde, pgozvs, joryqqt\n" +
            "qrzofif (66)\n" +
            "dgfjw (58)\n" +
            "lnxxnux (60)\n" +
            "eveoo (40)\n" +
            "khjbclb (581) -> tespp, kmjbuf, phcizz\n" +
            "eawuhm (98)\n" +
            "lrkttbz (35)\n" +
            "qtyeiv (97)\n" +
            "owxcxd (243) -> eppcdr, lkfhfri, ytjuerk\n" +
            "hjibopk (76)\n" +
            "lgpgt (114) -> saqta, qjjjm\n" +
            "kuqmkzc (9)\n" +
            "fyvrp (78)\n" +
            "qoyhxl (72) -> vgqxpp, phqtbfn\n" +
            "getbk (40)\n" +
            "qmryr (87)\n" +
            "hdaii (21)\n" +
            "jzlquwl (65) -> ymnissn, kkeqyo, fozofs\n" +
            "wopvp (231) -> mfngva, dmawkm\n" +
            "xpuly (14)\n" +
            "cyrupz (55) -> whbqia, sopjux, cfcdlh, wuluv, uwmocg, xtcpynj, qjvtm\n" +
            "tkntd (45) -> fscqnmn, fodfvds\n" +
            "sqsyxpe (119) -> djvozoc, otdcfi\n" +
            "waangt (46) -> ohgok, ydndqy\n" +
            "tvhkggh (294) -> uhvtx, utsyk\n" +
            "cipzed (14)\n" +
            "ykjng (1685) -> kukkmrx, xriycgo, djdiyl\n" +
            "leccfrx (8) -> jzeeng, vdlhr, kazqqf, cnvce\n" +
            "pmxdc (20)\n" +
            "jafvw (395) -> gznxb, suwehi, brrvjn\n" +
            "zyuuri (70) -> jnenzeu, brsgom, xuknyi, ggttj, vbukg, lhcix\n" +
            "csvhlkb (88)\n" +
            "xsbjwz (35)\n" +
            "bssnhty (53)\n" +
            "qtmszjy (86)\n" +
            "tsdgx (20)\n" +
            "trabc (52)\n" +
            "ynsjo (190) -> zxymd, wxruhqa, ckatuf, hablmj\n" +
            "svxpof (51)\n" +
            "wpzcj (20)\n" +
            "pcdpxv (36)\n" +
            "vjfkl (96)\n" +
            "chzno (264) -> lrqskol, mcponby\n" +
            "ounsg (40)\n" +
            "ucozsnh (26)\n" +
            "ikdpgy (33)\n" +
            "zyxswnx (86)\n" +
            "ensngra (56)\n" +
            "emuysfg (50)\n" +
            "vqmckky (80)\n" +
            "eecdh (57)\n" +
            "yafxw (130) -> lpxcjq, qxkcxj\n" +
            "jnpwbjj (73)\n" +
            "prfyjj (283) -> kljhkzg, mgmmqpf\n" +
            "vgbxwsh (85)\n" +
            "tespp (113)\n" +
            "jyxztzm (56)\n" +
            "hkjbbs (23)\n" +
            "rxmvx (234) -> ljpyq, xbnbi, uwisk\n" +
            "apbzweg (21)\n" +
            "pnxmraq (6)\n" +
            "fnkxzn (1197) -> mwmguqn, igjhbnk\n" +
            "ytgqzq (62) -> ddiqs, jgdqth\n" +
            "oircg (12)\n" +
            "pmytxie (48)\n" +
            "pprsdm (15)\n" +
            "synlyuo (165) -> adzhm, ciizgft\n" +
            "vxbieux (86)\n" +
            "zoagmd (58)\n" +
            "nuomen (121) -> hdaii, zfufrfx, noksmmp, fcpmz\n" +
            "djvozoc (24)\n" +
            "mbora (95)\n" +
            "uokdqce (16)\n" +
            "brxmlaa (47)\n" +
            "bcxne (66)\n" +
            "twhnzo (53)\n" +
            "nhefqs (139) -> cpnwxr, erlwita, raaqp\n" +
            "usmhoj (159) -> vommsj, fhpik\n" +
            "wlkzwch (87) -> axouzs, bkjpa, opath, wpxaup, ejehv, qdnmni, uggjwfl\n" +
            "jcalbf (90)\n" +
            "mhzpj (17)\n" +
            "fhsxx (33)\n" +
            "mzfmw (206) -> wwwep, yitoepf, idgrh, nqjsdi\n" +
            "hqqcmi (86)\n" +
            "fscnkbm (100) -> kyspusd, iipyba\n" +
            "asgmujz (270) -> sqypk, pzoji\n" +
            "tznvgv (87)\n" +
            "ihquo (45)\n" +
            "dwuakz (35)\n" +
            "ychlsfn (61)\n" +
            "ksledpc (146) -> tqefb, oglut\n" +
            "lpweyw (54) -> syzptux, rruyk\n" +
            "nxfjeuy (87)\n" +
            "dgebx (37)\n" +
            "sqypk (47)\n" +
            "grgkrf (1515) -> rxbohqw, ijhvxea, tzhto\n" +
            "ywuqf (57)\n" +
            "ihtcuq (70) -> uafzt, ounuwsx, nakiug\n" +
            "akwdkl (32)";
}
