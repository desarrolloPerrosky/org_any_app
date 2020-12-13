package cl.perrosky.organizapp.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import cl.perrosky.organizapp.model.Modelo;

public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "organizapp.db";
    private static final int VERSION = 1;
    private static final String TAG = "DbOpenHelper";

    public DbOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Modelo.CATEGORIA.getCreate());
        db.execSQL(Modelo.USUARIO.getCreate());
        db.execSQL(Modelo.MARCA.getCreate());
        db.execSQL(Modelo.PRODUCTO.getCreate());
        Log.i(TAG, "Base de datos creada");

        db.execSQL(Modelo.MARCA.getInsert("1  , 'PFIZER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("2  , 'VICHY', ''"));
        db.execSQL(Modelo.MARCA.getInsert("3  , 'ROCHE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("4  , 'PHARMA INVESTI', ''"));
        db.execSQL(Modelo.MARCA.getInsert("5  , 'TECNOFARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("6  , 'DRUGTECH', ''"));
        db.execSQL(Modelo.MARCA.getInsert("7  , 'PASTEUR', ''"));
        db.execSQL(Modelo.MARCA.getInsert("8  , 'CHILE LAB.', ''"));
        db.execSQL(Modelo.MARCA.getInsert("9  , 'ANDROMACO', ''"));
        db.execSQL(Modelo.MARCA.getInsert("10 , 'SAVAL', ''"));
        db.execSQL(Modelo.MARCA.getInsert("11 , 'K2 HEALTH & WEL', ''"));
        db.execSQL(Modelo.MARCA.getInsert("12 , 'COMPLEX THERAP.', ''"));
        db.execSQL(Modelo.MARCA.getInsert("13 , 'XIMENA POLANCO', ''"));
        db.execSQL(Modelo.MARCA.getInsert("14 , 'EUROMED', ''"));
        db.execSQL(Modelo.MARCA.getInsert("15 , 'BOEHRINGER I.', ''"));
        db.execSQL(Modelo.MARCA.getInsert("16 , 'GLAXOSMITHKLINE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("17 , 'EUROLAB', ''"));
        db.execSQL(Modelo.MARCA.getInsert("18 , 'SANOFI PASTEUR', ''"));
        db.execSQL(Modelo.MARCA.getInsert("19 , 'GYNOPHARM', ''"));
        db.execSQL(Modelo.MARCA.getInsert("20 , 'BAGO', ''"));
        db.execSQL(Modelo.MARCA.getInsert("21 , 'NOVARTIS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("22 , 'KAMPAR', ''"));
        db.execSQL(Modelo.MARCA.getInsert("23 , 'SYNTHON', ''"));
        db.execSQL(Modelo.MARCA.getInsert("24 , 'BIOSANO', ''"));
        db.execSQL(Modelo.MARCA.getInsert("25 , 'GENFAR', ''"));
        db.execSQL(Modelo.MARCA.getInsert("26 , 'CHEMOPHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("27 , 'OPKO CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("28 , 'RECALCINE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("29 , 'MINTLAB', ''"));
        db.execSQL(Modelo.MARCA.getInsert("30 , 'SANDERSON', ''"));
        db.execSQL(Modelo.MARCA.getInsert("31 , 'RAFFO', ''"));
        db.execSQL(Modelo.MARCA.getInsert("32 , 'RECCIUS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("33 , 'VALMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("34 , 'CARDIOPHARM', ''"));
        db.execSQL(Modelo.MARCA.getInsert("35 , 'URIAGE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("36 , 'ASPEN CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("37 , 'SANITAS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("38 , 'ALCON', ''"));
        db.execSQL(Modelo.MARCA.getInsert("39 , 'NEUMOBIOTICS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("40 , 'D & M PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("41 , 'HOSPIFARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("42 , 'FARMAC. ESAN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("43 , 'MEDIPHARM', ''"));
        db.execSQL(Modelo.MARCA.getInsert("44 , 'MEDIDERM', ''"));
        db.execSQL(Modelo.MARCA.getInsert("45 , 'MAVER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("46 , 'FOUCHARD', ''"));
        db.execSQL(Modelo.MARCA.getInsert("47 , 'MERCK SHARP & D', ''"));
        db.execSQL(Modelo.MARCA.getInsert("48 , 'ROEMMERS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("49 , 'ALLERGAN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("50 , 'DEUTSCHE PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("51 , 'BAYER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("52 , 'ABBOTT', ''"));
        db.execSQL(Modelo.MARCA.getInsert("53 , 'SANOFI AVENTIS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("54 , 'SILESIA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("55 , 'WYETH', ''"));
        db.execSQL(Modelo.MARCA.getInsert("56 , 'ASCEND', ''"));
        db.execSQL(Modelo.MARCA.getInsert("57 , 'FRESENIUS KABI', ''"));
        db.execSQL(Modelo.MARCA.getInsert("58 , 'B.BRAUN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("59 , 'ROERIG', ''"));
        db.execSQL(Modelo.MARCA.getInsert("60 , 'GARDEN HOUSE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("61 , 'PRATER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("62 , 'PHARMATECH', ''"));
        db.execSQL(Modelo.MARCA.getInsert("63 , 'ELI LILLY', ''"));
        db.execSQL(Modelo.MARCA.getInsert("64 , 'INTERPHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("65 , 'FARMO QUIMICA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("66 , 'KNOP', ''"));
        db.execSQL(Modelo.MARCA.getInsert("67 , 'ETEX', ''"));
        db.execSQL(Modelo.MARCA.getInsert("68 , 'GENZYME', ''"));
        db.execSQL(Modelo.MARCA.getInsert("69 , 'GRIFOLS-CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("70 , 'KEDRION', ''"));
        db.execSQL(Modelo.MARCA.getInsert("71 , 'GADOR CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("72 , 'ITF - LABOMED', ''"));
        db.execSQL(Modelo.MARCA.getInsert("73 , 'NOVO NORDISK', ''"));
        db.execSQL(Modelo.MARCA.getInsert("74 , 'ROYAL PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("75 , 'SWEDPHARM', ''"));
        db.execSQL(Modelo.MARCA.getInsert("76 , 'GRÜNENTHAL', ''"));
        db.execSQL(Modelo.MARCA.getInsert("77 , 'MASTERCARE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("78 , 'BMS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("79 , 'BIOTEC CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("80 , 'S.M.B.FARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("81 , 'HLB PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("82 , 'NICOLICH', ''"));
        db.execSQL(Modelo.MARCA.getInsert("83 , 'AXON PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("84 , 'MERCK', ''"));
        db.execSQL(Modelo.MARCA.getInsert("85 , 'ASTRAZENECA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("86 , 'GALDERMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("87 , 'DISPOLAB', ''"));
        db.execSQL(Modelo.MARCA.getInsert("88 , 'DENTAID', ''"));
        db.execSQL(Modelo.MARCA.getInsert("89 , 'STIEFEL', ''"));
        db.execSQL(Modelo.MARCA.getInsert("90 , 'FERRER CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("91 , 'HEEL CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("92 , 'GALENICUM', ''"));
        db.execSQL(Modelo.MARCA.getInsert("93 , 'DROG. HOFMANN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("94 , 'PFIZER CONSUMER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("95 , 'ISDIN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("96 , 'PENTAFARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("97 , 'ICLOS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("98 , 'HELSINN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("99 , 'RECKITT BENCK.', ''"));
        db.execSQL(Modelo.MARCA.getInsert("100, 'BEIERSDORF', ''"));
        db.execSQL(Modelo.MARCA.getInsert("101, 'PMG PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("102, 'BERNA BIOTECH', ''"));
        db.execSQL(Modelo.MARCA.getInsert("103, 'EXELTIS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("104, 'JANSSEN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("105, 'LUNDBECK CHILE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("106, 'ALPES CHEMIE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("107, 'BAXTER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("108, 'KIN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("109, 'JOHNSON&JOHNSON', ''"));
        db.execSQL(Modelo.MARCA.getInsert("110, 'PHARMAVITA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("111, 'CASSARA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("112, 'BIOTOSCANA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("113, 'POEN', ''"));
        db.execSQL(Modelo.MARCA.getInsert("114, 'PHARMA BRAND H.', ''"));
        db.execSQL(Modelo.MARCA.getInsert("115, 'NEWSCIENCE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("116, 'NEILMED', ''"));
        db.execSQL(Modelo.MARCA.getInsert("117, 'NESTLE', ''"));
        db.execSQL(Modelo.MARCA.getInsert("118, 'OPHTHA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("119, 'GADOR PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("120, 'NOVOPLOS', ''"));
        db.execSQL(Modelo.MARCA.getInsert("121, 'ANDES PHARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("122, 'FAES FARMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("123, 'CYRSALAB', ''"));
        db.execSQL(Modelo.MARCA.getInsert("124, 'ALLERGIKA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("125, 'GEDEON RICHTER', ''"));
        db.execSQL(Modelo.MARCA.getInsert("126, 'GEBRO', ''"));
        db.execSQL(Modelo.MARCA.getInsert("127, 'BAMBERG', ''"));
        db.execSQL(Modelo.MARCA.getInsert("128, 'TS GROUP', ''"));
        db.execSQL(Modelo.MARCA.getInsert("129, 'FARM.SCHUBERT', ''"));
        db.execSQL(Modelo.MARCA.getInsert("130, 'GENERAL ELECT.', ''"));
        db.execSQL(Modelo.MARCA.getInsert("131, 'MODERMA', ''"));
        db.execSQL(Modelo.MARCA.getInsert("132, 'BOSTON', ''"));
        db.execSQL(Modelo.MARCA.getInsert("133, 'MEDAC', ''"));
        db.execSQL(Modelo.MARCA.getInsert("134, 'MEDSTYLE', ''"));

        db.execSQL(Modelo.CATEGORIA.getInsert( "1, 'Analgesicos opiáceos','En este grupo de tipos de medicamentos se encuentran todos los fármacos que tienen como finalidad aliviar el dolor físico, ya sea de cabeza, de articulaciones o cualquiera.\n\nson de acción más potente, no están permitidos en la automedicación y pueden generar dependencia (como la morfina)'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "2, 'Analgesicos no opiáceos','En este grupo de tipos de medicamentos se encuentran todos los fármacos que tienen como finalidad aliviar el dolor físico, ya sea de cabeza, de articulaciones o cualquiera.\n\nincluyen tanto los AntiInflamatorios No Esteroides (AINE), como el ibuprofeno y la aspirina o el paracetamol. Entre los efectos secundarios generales de los AINES están que favorecen el desarrollo de úlceras, pueden provocar complicaciones renales y aumentar la presión sanguínea.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "3, 'Antiacidos', 'provoca la disminución de las secreciones gástricas. Si disminuye la acidez, se previene la aparición de úlceras. Un ejemplo conocido es el Omeprazol.\n\nEn estos fármacos, los efectos secundarios más importantes son las alteraciones del tránsito intestinal (diarrea o estreñimiento).'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "4, 'Antialérgicos', 'En esta categoría se agrupan fármacos que tienen la finalidad de combatir los efectos negativos de las reacciones alérgicas o la hipersensibilidad.\n\nLos más populares son los fármacos de la familia de antihistamínicos, cuyo mecanismo de acción influye sobre la histamina, la cual tiene un importante papel en la alergias. Sus reacciones adversas son mínimas, pero puede ocasionar diarrea, somnolencia, fatiga o cefaleas.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "5, 'Antidiarreicos', 'Los antidiarreicos son tipos de medicamentos que tienen como propósito aliviar y detener los efectos de la diarrea. Los más utilizados actualmente son fármacos que inhiben la motilidad del intestino, lo que favorece la retención para conseguir una mayor consistencia y volumen en las heces. Las reacciones adversas de estos fármacos son mínimas, aunque se han registrado algunas como el dolor abdominal o el estreñimiento.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "6, 'Laxantes', 'Los laxantes son recetados para el caso contrario, en otras palabras, para resolver problemas de estreñimiento por un aumento del movimiento intestinal o por lubricación. Su uso debe ser moderado y como apoyo, porque un tratamiento prolongado hace que el intestino no trabaje correctamente, disminuyendo su capacidad de absorción de nutrientes.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "7, 'Antiinfecciosos', 'Este tipo de medicamentos están recetados para hacer frente a infecciones. Dependiendo del agente infeccioso, se clasifican en antibióticos (contra bacterias), antifúngicos (contra hongos), antivirales (contra virus) y antiparasitarios (contra parásitos).'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "8, 'Antiinflamatorios', 'Como el propio nombre indica, son fármacos que tienen como finalidad reducir los efecto de la inflamación. Los más recetados son los catalogados como AINES, que además de disminuir la inflamación, tienen efectos analgésicos.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "9, 'Antipiréticos', 'Los fármacos antipiréticos son un tipo de medicamentos que tienen la capacidad de reducir la fiebre. Entre los más conocidos están la aspirina, el ibuprofeno y el paracetamol, que también presentan otras funciones.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "10, 'Antitusivos', 'Son fármacos que se recetan para tratar de reducir la tos no productiva, es decir, que no libera mucosidad. Se debe tener sumo cuidado con su dosis, ya que algunos de ellos, como la codeína, producen adicción.'"));
        db.execSQL(Modelo.CATEGORIA.getInsert( "11, 'Mucoliticos', 'son medicamentos que se recomiendan cuando la mucosidad dificulta una respiración correcta. Sus efectos secundarios son menores, como cefaleas o reacciones alérgicas.'"));


        db.execSQL(Modelo.PRODUCTO.getInsert("1, 'Analgex', 'tramadol clorhidrato', '7800026245781', 1, 1, 1"));
        db.execSQL(Modelo.PRODUCTO.getInsert("2, 'Zopinom', 'eszopiclona 3mg', '7800007747211', 30, 2, 2"));

        Log.i(TAG, "Base de datos poblada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Base de datos actualizada desde la version [" + oldVersion +" hacia [" + newVersion + "]");
    }
}
