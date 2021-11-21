public class Test {

    public static void main(String[] args) {
        System.out.println("Test zu Obertyp Tree\n");

        System.out.println("\nTest zu Obertyp Tree, Untertyp FagusSylvatica\n");
        Tree FagusTestTree = new FagusSylvatica(10, 20, 50);
        System.out.println("Methode: species()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " +  islatinName(FagusTestTree.species()));
        System.out.println("Methode: size()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(FagusTestTree.size()));
        //VORB: Vorbedingung im Obertyp: size + change > 0 wird eingehalten: 10 - 9.5 = 0.5 -> eingehalten
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Vorbedingung im Untertyp schwächer oder gleich ist: " + isvalidchange(-9.5f, FagusTestTree.size(),1));
        FagusTestTree.changeSize(-9.5f);
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(FagusTestTree.size()));

        System.out.println("\nTest zu Obertyp Tree, Untertyp Quercus Petraea\n");
        Tree QuercusPTestTree = new QuercusPetraea(10, 20, 50);
        System.out.println("Methode: species()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " +  islatinName(QuercusPTestTree.species()));
        System.out.println("Methode: size()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(QuercusPTestTree.size()));
        //VORB: Vorbedingung im Obertyp: size + change > 0 wird eingehalten: 10 - 9.5 = 0.5 -> eingehalten
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Vorbedingung im Untertyp schwächer oder gleich ist: " + isvalidchange(-9.5f, QuercusPTestTree.size(),1));
        QuercusPTestTree.changeSize(-9.5f);
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(QuercusPTestTree.size()));

        System.out.println("\nTest zu Obertyp Tree, Untertyp Quercus Robur\n");
        Tree QuercusRTestTree = new QuercusRobur(10, 20, 50);
        System.out.println("Methode: species()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " +  islatinName(QuercusRTestTree.species()));
        System.out.println("Methode: size()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(QuercusRTestTree.size()));
        //VORB: Vorbedingung im Obertyp: size + change > 0 wird eingehalten: 10 - 9.5 = 0.5 -> eingehalten
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Vorbedingung im Untertyp schwächer oder gleich ist: " + isvalidchange(-9.5f, QuercusRTestTree.size(),1));
        QuercusRTestTree.changeSize(-9.5f);
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(QuercusRTestTree.size()));

        System.out.println("\nTest zu Obertyp Tree, Untertyp Carpinus Betulus\n");
        Tree CarpinusTestTree = new CarpinusBetulus(10, 20, 50);
        System.out.println("Methode: species()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " +  islatinName(CarpinusTestTree.species()));
        System.out.println("Methode: size()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(CarpinusTestTree.size()));
        //VORB: Vorbedingung im Obertyp: size + change > 0 wird eingehalten: 10 - 9.5 = 0.5 -> eingehalten
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Vorbedingung im Untertyp schwächer oder gleich ist: " + isvalidchange(-9.5f, CarpinusTestTree.size(),1));
        CarpinusTestTree.changeSize(-9.5f);
        System.out.println("Methode: changeSize()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidSize(CarpinusTestTree.size()));

        System.out.println("\nTest zum Obertyp Fagaceae\nMethoden vom Obertyp werden im Interface Fagaceae nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp Fagaceae, Untertyp FagusSylvatica\n");
        Fagaceae FagusTestFagacea = new FagusSylvatica(10, 20, 50);
        System.out.println("Methode: family()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isFamilyFagaceae(FagusTestFagacea.family()));

        System.out.println("\nTest zu Obertyp Fagaceae, Untertyp Quercus Petraea\n");
        Fagaceae QuercusPTestFagaceae = new QuercusPetraea(10, 20, 50);
        System.out.println("Methode: family()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isFamilyFagaceae(QuercusPTestFagaceae.family()));

        System.out.println("\nTest zu Obertyp Fagaceae, Untertyp Quercus Robur\n");
        Fagaceae QuercusRTestFagaceae = new QuercusRobur(10, 20, 50);
        System.out.println("Methode: family()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isFamilyFagaceae(QuercusRTestFagaceae.family()));

        System.out.println("\nTest zum Obertyp Quercus\nMethoden vom Obertypen werden im Interface Quercus nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp Quercus, Untertyp Quercus Petraea\n");
        Quercus QuercusPTestQuercus = new QuercusPetraea(10, 20, 50);
        System.out.println("Methode: genus()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isGenusQuercus(QuercusPTestQuercus.genus()));

        System.out.println("\nTest zu Obertyp Quercus, Untertyp Quercus Robur\n");
        Quercus QuercusRTestQuercus = new QuercusRobur(10, 20, 50);
        System.out.println("Methode: genus()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isGenusQuercus(QuercusRTestQuercus.genus()));

        System.out.println("\nTest zum Obertyp LightDemanding\nMethoden vom Obertypen werden im Interface LightDemanding nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp LightDemanding, Untertyp Quercus Petraea\n");
        LightDemanding QuercusPTestLightDemanding = new QuercusPetraea(10, 20, 50);
        System.out.println("Methode: trunkSlope()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidtrunkSlope(QuercusPTestLightDemanding.trunkSlope()));

        System.out.println("\nTest zu Obertyp LightDemanding, Untertyp Quercus Robur\n");
        LightDemanding QuercusRTestLightDemanding = new QuercusRobur(10, 20, 50);
        System.out.println("Methode: trunkSlope()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidtrunkSlope(QuercusRTestLightDemanding.trunkSlope()));

        System.out.println("\nTest zum Obertyp Domestic\nMethoden vom Obertypen werden im Interface LightDemanding nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp Domestic, Untertyp FagusSylvatica\n");
        Domestic FagusTestDomestic = new FagusSylvatica(10, 20, 50);
        System.out.println("Methode: latitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(FagusTestDomestic.latitude()));
        System.out.println("Methode: longitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlongitude(FagusTestDomestic.longitude()));


        System.out.println("\nTest zu Obertyp Domestic, Untertyp Quercus Petraea\n");
        Domestic QuercusPTestDomestic = new QuercusPetraea(10, 20, 50);
        System.out.println("Methode: latitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(QuercusPTestDomestic.latitude()));
        System.out.println("Methode: longitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlongitude(QuercusPTestDomestic.longitude()));

        System.out.println("\nTest zu Obertyp Domestic, Untertyp Quercus Robur\n");
        Domestic QuercusRTestDomestic = new QuercusRobur(10, 20, 50);
        System.out.println("Methode: latitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(QuercusRTestDomestic.latitude()));
        System.out.println("Methode: longitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlongitude(QuercusRTestDomestic.longitude()));

        System.out.println("\nTest zu Obertyp Domestic, Untertyp Carpinus Betulus\n");
        Domestic CarpinusTestDomestic = new CarpinusBetulus(10, 20, 50);
        System.out.println("Gibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(CarpinusTestDomestic.latitude()));
        System.out.println("Gibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlongitude(CarpinusTestDomestic.longitude()));

        System.out.println("\nTest zum Obertyp ContinentalClimate\nMethoden vom Obertypen werden im Interface LightDemanding nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp ContinentalClimate, Untertyp Quercus Petraea\n");
        ContinentalClimate QuercusPTestContinentalClimate = new QuercusPetraea(10, 20, 50);
        System.out.println("Methode: incidence()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidincidence(QuercusPTestContinentalClimate.incidence()));

        System.out.println("\nTest zu Obertyp ContinentalClimate, Untertyp Quercus Robur\n");
        ContinentalClimate QuercusRTestContinentalClimate = new QuercusRobur(10, 20, 50);
        System.out.println("Methode: incidence()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(QuercusRTestContinentalClimate.incidence()));

        System.out.println("\nTest zu Obertyp ContinentalClimate, Untertyp Carpinus Betulus\n");
        ContinentalClimate CarpinusTestContinentalClimate = new CarpinusBetulus(10, 20, 50);
        System.out.println("Methode: incidence()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(CarpinusTestContinentalClimate.incidence()));


        System.out.println("\n \n Test zum Forbidden Tree als Paradebeispiel für Missachtung der Untertypbeziehungen");

        System.out.println("Test zu Obertyp Tree\n");

        System.out.println("\nTest zu Obertyp Tree, Untertyp L7ForbiddenTree\n");
        Tree ForbiddenTestTree = new L7ForbiddenTree(-1.0f);
        System.out.println("Methode: species()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " +  islatinName(ForbiddenTestTree.species()));
        System.out.println("Methode: size()\nGibt true zurück wenn die Invariante im Obertyp auch im Untertyp stärker oder gleich ist: " + isvalidSize(ForbiddenTestTree.size()));
        //VORB: Vorbedingung im Obertyp: size + change > 0 wird eingehalten: -1.0f + 1.2 = 0.2 -> eingehalten
        System.out.println("Methode: changeSize()\nGibt true zurück wenn die Vorbedingung im Untertyp schwächer oder gleich ist: " + isvalidchange(1.2f, ForbiddenTestTree.size(),2));
        ForbiddenTestTree.changeSize(2.0f);//durch Einhaltung der VORB im Untertyp (size + change > 1), wird Invariante in Obertyp wieder erfüllt
        System.out.println("Methode: changeSize()\nGibt true aus wenn die Invariante im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidSize(ForbiddenTestTree.size()));

        System.out.println("\nTest zum Obertyp Fagaceae\nMethoden vom Obertyp werden im Interface Fagaceae nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp Fagaceae, Untertyp L7ForbiddenTree\n");
        Fagaceae ForbiddenTestFagacea = new L7ForbiddenTree(-0.8f);
        System.out.println("Methode: family()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isFamilyFagaceae(ForbiddenTestFagacea.family()));

        System.out.println("\nTest zum Obertyp Quercus\nMethoden vom Obertypen werden im Interface Quercus nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp Quercus, Untertyp L7ForbiddenTree\n");
        Quercus ForbiddenTestQuercus = new L7ForbiddenTree(-0.8f);
        System.out.println("Methode: genus()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isGenusQuercus(ForbiddenTestQuercus.genus()));

        System.out.println("\nTest zum Obertyp LightDemanding\nMethoden vom Obertypen werden im Interface LightDemanding nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp LightDemanding, Untertyp L7ForbiddenTree\n");
        LightDemanding ForbiddenTestLightDemanding = new L7ForbiddenTree(-0.8f);
        System.out.println("Methode: trunkSlope()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidtrunkSlope(ForbiddenTestLightDemanding.trunkSlope()));

        System.out.println("\nTest zum Obertyp Domestic\nMethoden vom Obertypen werden im Interface LightDemanding nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp Domestic, Untertyp L7ForbiddenTree\n");
        Domestic ForbiddenTestDomestic = new L7ForbiddenTree(-0.8f);
        System.out.println("Methode: latitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlatitude(ForbiddenTestDomestic.latitude()));
        System.out.println("Methode: longitude()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidlongitude(ForbiddenTestDomestic.longitude()));

        System.out.println("\nTest zum Obertyp ContinentalClimate\nMethoden vom Obertypen werden im Interface LightDemanding nicht überschrieben und müssen deswegen nicht nochmal überprüft werden");

        System.out.println("\nTest zu Obertyp ContinentalClimate, Untertyp L7ForbiddenTree\n");
        ContinentalClimate ForbiddenTestContinentalClimate = new L7ForbiddenTree(-0.8f);
        System.out.println("Methode: incidence()\nGibt true aus wenn Nachbedingung im Obertyp auch im Untertyp stärker bzw. gleich ist: " + isvalidincidence(ForbiddenTestContinentalClimate.incidence()));
    }

    //KOMMENTAR: Testet die Invariante von Tree
    //NACHB: Gibt false zurück wenn size kleinergleich 0 ist, true sonst
    public static boolean isvalidSize(float size){
        if (size <= 0 ){
            return false;
        }
        return true;
    }

    //KOMMENTAR: Testet Nachbedingung der methode species
    //VORB: name != null
    //NACHB: true wenn name einer der möglichen lateinischen Bezeichnungen ist, false sonst
    public static boolean islatinName(String name){
        if (name.equals("Fagus Sylvatica") || name.equals("Quercus Robur") || name.equals("Quercus Petraea") || name.equals("Carpinus Betulus")){
            return true;
        }
        return false;
    }

    //KOMMENTAR: Testet Vorbedingung von changeSize
    //VORB: type = 1 oder type = 2
    //NACHB: wenn type = 1: dann lautet die Vorbedingung im Untertyp: size+change > 0
    //                      es wird true zurückgegeben, wenn size + change > 0, sonst Rüchgabe false
    //       wenn type = 2: dann lautet die Vorbedingung im Untertyp: size+change > 1
    //                      es wird true zurückgegeben, wenn size + change > 1, sonst Rüchgabe false
    public static boolean isvalidchange(float change, float size, int type){
        if(type == 1){
            if (size + change > 0){
                return true;
            }
            return false;
        }else{
            if (size + change > 1){
                return true;
            }
            return false;
        }
    }

    //KOMMENTAR: Testet die Nachbedingung von family
    //VORB: family != null
    //NACHB: Gibt true aus wenn die family Fagaceae ist, sonst false
    public static boolean isFamilyFagaceae(String family){
        if(family.equals("Fagaceae")){
            return true;
        }
        return false;
    }

    //KOMMENTAR: Testet die Nachbedingung von genus
    //VORB: genus != null
    //NACHB: Gibt true aus wenn der genus ist, sonst false
    public static boolean isGenusQuercus(String genus){
        if(genus.equals("Quercus")){
            return true;
        }
        return false;
    }

    //KOMMENTAR: Testet die Nachbedingung von trunkSlope
    //NACHB: Gibt true zurück wenn trunkSlope zwischen 0 und 1 liegt, sonst false
    public static boolean isvalidtrunkSlope(float trunkSlope){
        if (Float.compare(0f, trunkSlope) < 0 && Float.compare(1f, trunkSlope) > 0){
            return true;
        }
        return false;
    }

    //KOMMENTAR: Testet die Nachbedingung von longitude
    //NACHB: Gibt true zurück wenn longitude zwischen -180 und 180 liegt, sonst false
    public static boolean isvalidlongitude(float longitude){
        if (Float.compare(-180f, longitude) < 0 && Float.compare(180f, longitude) > 0){
            return true;
        }
        return false;
    }

    //KOMMENTAR: Testet die Nachbedingung von latitude
    //NACHB: Gibt true zurück wenn latitude zwischen -90 und 90 liegt, sonst false
    public static boolean isvalidlatitude(float latitude){
        if (Float.compare(-90f, latitude) < 0 && Float.compare(90f, latitude) > 0){
            return true;
        }
        return false;
    }

    //KOMMENTAR: Testet Nachbedingung von incidence
    //NACHB: True wenn incidence größer als 0 ist, sonst false
    public static boolean isvalidincidence(float incidence){
        if (Float.compare(0f, incidence) >= 0){
            return false;
        }
        return true;
    }
}

/*
Begründung für nicht bestehende Untertypbeziehungen:

FagusSylvatica:
    FagusSylvatica ist kein Untertyp von LightDemanding, weil die Rotbuche laut Angabe auch in stark verschatteten Bereichen
    wächst und somit der Forderung der Lichtbaumart, direktes Sonnenlicht zu haben, widerspricht.

    FagusSylvatica ist kein Untertyp von ContinentalClimate, da die Rotbuche entsprechend der Beschreibung von ContinentalClimate
    als Zeigerpflanze für ozeanischen Einfluss gilt (folglich kann sie nur unter ozeanischem Einfluss gedeihen) und somit
    nicht den Anforderungen für ContinentalClimate entspricht.

    FagusSylvatica ist kein Untertyp von Quercus, da die Rotbuche nicht der Gattung der Eichen angehört.

CarpinusBetulus:
    CarpinusBetulus ist kein Untertyp von Fagaceae, da die Hainbuche nicht zur Familie der Buchengewächse gehört, sondern
    zur Familie der Birkengewächse (diese wird nicht als Typ angeführt).

    CarpinusBetulus ist kein Untertyp von Quercus, da sich das bereits aus der zuvor nicht vorhandenen Untertypbeziehung zu
    Fagaceae transitiv ableiten lässt.

    CarpinusBetulus ist kein Untertyp von LightDemanding, da sie laut Angabe Schatten so gut wie Rotbuchen verträgt und
    letztere keine Lichtbaumart ist.

Fagaceae:
    Fagaceae ist kein Untertyp von LightDemanding, da mindestens eine Baumart der Familie Fagaceae existiert, die keine
    Lichtbaumart ist (= FagusSylvatica).

    Fagaceae ist kein Untertyp von Domestic, da es innerhalb dieser Familie auch Baumarten gibt, die nicht in Europa und somit
    nicht Österreich heimisch sind.

    Fagaceae ist kein Untertyp von ContinentalClimate, da dies transitiv aus dem vorigen Satz folgt.

Domestic:
    Domestic ist kein Untertyp von Fagaceae, weil es mindestens eine Baumart gibt (= CarpinusBetulus), die nicht der Familie
    der Fagaceae angehört und trotzdem heimisch ist.

    Domestic ist kein Untertyp von Quercus, da das transitiv aus dem vorigen Satz folgt.

    Domestic ist kein Untertyp von LightDemanding, weil nicht alle hemischen Baumarten zu den Lichtbäumen zählen (zum Bsp CarpinusBetulus).

ContinentalClimate:
    ContinentalClimate ist kein Untertyp von Fagaceae, da es mindestens eine Baumart gibt (= CarpinusBetulus), die nicht der Familie
    der Fagaceae angehört und trotzdem heimisch ist.

    ContinentalClimate ist kein Untertyp von Quercus, da das transitiv aus dem vorigen Satz folgt.

    ContinentalClimate ist kein Untertyp von LightDemanding, da nicht alle Baumarten, die unter kontinentalem Einfluss heimisch sind,
    Lichtbaumarten sind (zum Bsp CarpinusBetulus).

LightDemanding:
    LightDemanding ist kein Untertyp von Domestic, da LightDemanding laut Angabe nur besagt, dass ein Baum ein Lichtbaum ist,
    aber nicht festlegt, dass dieser auch heimisch sein muss.

    LightDemanding ist kein Untertyp von ContinentalClimate, da das transitiv aus dem vorigen Satz folgt.

    LightDemanding ist kein Untertyp von Fagaceae, da es auch andere Familien geben kann, die Baumarten besitzen, welche
    als Lichtbaum kategorisiert werden könnten.

    LightDemanding ist kein Untertyp von Quercus, da das transitiv aus dem vorigen Satz folgt.
 */