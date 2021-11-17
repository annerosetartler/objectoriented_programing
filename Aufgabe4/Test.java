public class Test {

    public static void main(String[] args) {
        System.out.println("hello");
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