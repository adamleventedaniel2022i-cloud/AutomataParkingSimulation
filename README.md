# Parkolóautomata Szimuláció

## Tartalomjegyzék

- [Projekt Áttekintés](#projekt-áttekintés)
- [Funkciók](#funkciók)
- [Technológiák](#technológiák)
- [Telepítés és Futtatás](#telepítés-és-futtatás)
- [Projekt Struktúra](#projekt-struktúra)
- [Tesztelés](#tesztelés)
- [Csapat](#csapat)

## 🎯 Projekt Áttekintés

Szoftvertesztelés tantárgy projektfeladataként készült Java alkalmazás, amely egy modern parkolóautomata működését szimulálja. A rendszer két üzemmódot kínál: felhasználói mód parkolási tranzakciókhoz és admin mód a rendszer karbantartásához.

### Főbb Jellemzők

- 🎫 **3 parkolási zóna** különböző árazással (A, B, C)
- 💰 **Készpénzes fizetés** 5 címlettel (100-2000 Ft)
- ⏱️ **Túlóra-büntetési rendszer** konfigurálható paraméterekkel
- 🔐 **Jelszóvédett admin mód** kassza- és rendszerkezeléshez
- 💾 **Fájl alapú perzisztencia** (config, balance, transactions)
- ✅ **100% tesztlefedettség** (22/22 sikeres teszt)

## 🚀 Funkciók

### Felhasználói Mód
- Parkolási zóna és időtartam megadása
- Automatikus díjszámítás túlóra-büntetéssel
- Címletenkénti fizetés
- Greedy algoritmusú visszajáró-kiadás
- Tranzakció naplózás

### Admin Mód
- 🔑 Jelszavas hitelesítés
- 💵 Kasszafeltöltés címletenként
- ⚙️ Rendszerparaméterek módosítása (kezdő egyenleg, limit, túlóra-együttható)
- 📊 Kassza egyenleg és címletkészlet megtekintése

## 🛠️ Technológiák

- **Nyelv:** Java SE 11+
- **Architektúra:** Moduláris OOP (7 csomag, 12 osztály)
- **Perzisztencia:** Text fájlok (`.txt`)
- **Tesztelés:** Manuális + Rendszer szintű (ISO/IEC/IEEE 29119-3)
- **Hibakezelés:** Egyedi exception osztályok

## 📦 Telepítés és Futtatás

### Követelmények
- Java 11 vagy újabb
- 50 MB szabad tárhely

### Klónozás és Futtatás

```bash
# Repository klónozása
git clone https://github.com/adamleventedaniel2022i-cloud/AutomataParkingSimulation.git
cd AutomataParkingSimulation

# Fordítás (rekurzívan minden .java fájlra)
javac -d bin src/**/*.java

# Futtatás
java -cp bin app.Main
```

## 📁 Projekt Struktúra

```
src/
├── admin/              # Admin mód logika
├── app/                # Main belépési pont
├── config/             # Konfiguráció kezelés
├── core/               # Üzleti logika (kassza, tranzakció, automata)
├── Exceptions/         # Egyedi kivételosztályok (4 db)
└── validation/         # Input validáció
```

**Fő komponensek:**
- `CashRegister` - Kassza címletkezelés
- `Transaction` - Díjszámítás és zóna logika
- `ParkingMachine` - Tranzakció- és visszajáró-folyamat
- `Admin` - Adminisztratív műveletek
- `Config` - Perzisztens konfiguráció és naplózás

## 🧪 Tesztelés

### Tesztelési Eredmények (v2.0)
```
✅ Teljes tesztesetek: 22
✅ Sikeres: 22 (100%)
❌ Sikertelen: 0
🐛 Javított hibák: 4 (BUG001-004)
```

### Tesztelt Területek
- Zóna validálás és díjszámítás
- Fizetési folyamat (címletek, visszajáró)
- Admin műveletek (jelszó, feltöltés, konfig)
- Hibakezelés (limitek, érvénytelen bemenetek)
- Perzisztencia (fájl írás/olvasás)

## 👥 Csapat

### Fejlesztő Csapat
- **Ádám Levente Dániel** - [@adamleventedaniel2022i-cloud](https://github.com/adamleventedaniel2022i-cloud)
- **Ménes Megyer Zétény** - [@RegyemSenem1425](https://github.com/RegyemSenem1425)
- **Mercz Bence László** - [@MerczBence](https://github.com/MerczBence)

### Tesztelő Csapat
- **Lőrincz Márton** - Tesztelés vezető
- **Csikós Bendegúz** - Tesztelő
- **Káldor Levente Viktor** - Projectvezető
- **Juhász Arnold** - Teszt tervező

---

## 📊 Projekt Statisztikák

- 📅 **Időtartam:** 2025. december 5. - 2026. január 7.
- 📝 **Kódsorok:** ~1500 sor Java
- 🧪 **Tesztek:** 22 eset, 100% lefedettség
- 📄 **Dokumentáció:** 7 dokumentum (specifikáció, fejlesztői, tesztelési)
- 🔄 **Verziók:** 4 kiadás (v1.0 → v2.0)

---

**Készült az 2022i Osztály Szoftvertesztelés kurzusának projektfeladataként** 🎓
