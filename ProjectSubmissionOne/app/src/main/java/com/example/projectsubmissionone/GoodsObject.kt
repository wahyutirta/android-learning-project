package com.example.projectsubmissionone

object GoodsObject {
    private val goodsName = arrayOf("Brokoli",
        "Kubis",
        "Wortel",
        "Lobak",
        "Selada",
        "Kembang Kol",
        "Terung",
        "Buncis",
        "Tomat",
        "Bawang Bombai")

    private val goodsDetails = arrayOf("Brokoli (Brassica oleracea L. Kelompok Italica) adalah tanaman sayuran yang termasuk dalam suku kubis-kubisan atau Brassicaceae. Brokoli berasal dari daerah Laut Tengah dan sudah sejak masa Yunani Kuno dibudidayakan. Sayuran ini masuk ke Indonesia belum lama (sekitar 1970-an) dan kini cukup populer sebagai bahan pangan.",
            "Kubis, kol, kobis, atau kobis bulat (terdiri dari beberapa kelompok kultivar dari Brassica oleracea) adalah tanaman dua tahunan hijau atau ungu berdaun, ditanam sebagai tanaman tahunan sayuran untuk kepala padat berdaunnya. Erat kaitannya dengan tanaman cole lainnya, seperti brokoli, kembang kol, dan kubis brussel, itu diturunkan dari B. oleracea var. oleracea, kubis lapangan liar. Kepala kubis umumnya berkisar 0,5 hingga 4 kilogram (1 hingga 9 pon), dan dapat berwarna hijau, ungu dan putih.",
            "Wortel adalah tumbuhan biennial (siklus hidup 12 - 24 bulan) yang menyimpan karbohidrat dalam jumlah besar untuk tumbuhan tersebut berbunga pada tahun kedua. Batang bunga tumbuh setinggi sekitar 1 m, dengan bunga berwarna putih, dan rasa yang manis langu. Bagian yang dapat dimakan dari wortel adalah bagian umbi atau akarnya. Lobak adalah tumbuhan yang masuk ke dalam famili Cruciferae. Bentuk umbi lobak seperti wortel, tetapi isi dan kulitnya berwarna putih. Tanaman lobak berasal dari Tiongkok, dan telah banyak diusahakan di Indonesia. Tanaman yang mudah ditanam baik di dataran rendah maupun pegunungan.",
            "Lobak, Saat ini daerah yang banyak ditanami lobak adalah dataran tinggi Pangalengan, Pacet, Cipanas, dan Bedugul. Luas areal tanaman lobak di Indonesia saat ini berkisar 15.700 hektare.",
            "Selada atau daun sla (Lactuca sativa) adalah tumbuhan sayur yang biasa ditanam di daerah beriklim sedang maupun daerah tropika. Kegunaan utama adalah sebagai salad. Produksi selada dunia diperkirakan sekitar 3 juta ton yang ditanam pada lebih dari 300.000 ha lahan.",
            "Kembang kol atau bunga kol merupakan tumbuhan yang termasuk dalam kelompok botrytis dari jenis Brassica oleracea (suku Brassicaceae). Sebagai sayuran, tumbuhan ini lazim dikenal sebagai kubis bunga yang merupakan terjemahan harafiah dari bahasa Belanda bloemkool. Kata blumbol juga dikenal secara lazim. Kubis bunga berbentuk mirip dengan brokoli. Perbedannya, kubis bunga memiliki kepala bunga yang banyak dan teratur dengan padat. Hanya 'kepala' kembang kol yang lazim dimakan.",
            "Terung ialah terna yang sering ditanam secara tahunan. Tanaman ini tumbuh hingga 40–150 cm (16-57 inci) tingginya. Daunnya besar, dengan lobus yang kasar. Ukurannya 10–20 cm (4-8 inci) panjangnya dan 5–10 cm (2-4 inci) lebarnya. Jenis-jenis setengah liar lebih besar dan tumbuh hingga setinggi 225 cm (7 kaki), dengan daun yang melebihi 30 cm (12 inci) dan 15 cm (6 inci) panjangnya. Batangnya biasanya berduri. Warna bunganya antara putih hingga ungu, dengan mahkota yang memiliki lima lobus.",
            "Buncis (dari bahasa Belanda boontjes untuk kacang-kacangan secara umum), adalah sejenis polong-polongan yang dapat dimakan dari berbagai kultivar Phaseolus vulgaris. Buah, biji, dan daunnya dimanfaatkan orang sebagai sayuran. Sayuran ini kaya dengan kandungan protein. Ia dipercaya berasal dari Amerika Tengah dan Amerika Selatan. Buncis adalah sayur yang kaya dengan protein dan vitamin ini membantu menurunkan tekanan darah serta mengawal metabolisme gula dalam darah dan amat sesuai dimakan oleh mereka yang mengidap penyakit diabetes atau hipertensi.",
            "Tomat (Solanum lycopersicum syn. Lycopersicum esculentum) adalah tumbuhan dari keluarga Solanaceae, tumbuhan asli Amerika Tengah dan Selatan, dari Meksiko sampai Peru. Tomat merupakan tumbuhan siklus hidup singkat, dapat tumbuh setinggi 1 sampai 3 meter. Tumbuhan ini memiliki buah berawarna hijau, kuning, dan merah yang biasa dipakai sebagai sayur dalam masakan atau dimakan secara langsung tanpa diproses. Tomat memiliki batang dan daun yang tidak dapat dikonsumsi karena masih sekeluarga dengan kentang dan Terung yang mengadung Alkaloid.",
            "Bawang bombai (Latin: Allium Cepa Linnaeus) adalah jenis bawang yang paling banyak dan luas dibudidayakan, dipakai sebagai bumbu maupun bahan masakan, berbentuk bulat besar dan berdaging tebal. Bawang bombai biasa digunakan dalam memasak makanan di Indonesia, tidak hanya digunakan sebagai hiasan tetapi juga bagian dari masakan karena bentuknya yang besar dan tebal dagingnya. Disebut bawang bombai karena dibawa oleh pedagang-pedagang yang berasal dari kota Bombai (Mumbai sekarang) di India"
            )

    private val goodsImg = intArrayOf(R.drawable.brokoli,
        R.drawable.kubis,
        R.drawable.wortel,
        R.drawable.lobak,
        R.drawable.selada,
        R.drawable.kembangkol,
        R.drawable.terong,
        R.drawable.buncis,
        R.drawable.tomat,
        R.drawable.bombai
    )

    val listData: ArrayList<Goods>
        get() {
            val list = arrayListOf<Goods>()
            for (position in goodsName.indices) {
                val goods = Goods()
                goods.name = goodsName[position]
                goods.detail = goodsDetails[position]
                goods.photo = goodsImg[position]
                list.add(goods)
            }
            return list
        }
}