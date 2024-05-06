<!-- INTRO -->
<br />
<div align="center">
  <h1 align="center">Tugas Kecil 3 IF2211 Strategi Algoritma Tahun Ajaran 2023/2024</h1>

  <p align="center">
    <h3> Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best-First Search, dan A* </h3>
    <p>Program made using Java Language</p>
    <br />
    <a href="https://github.com/zultopia/Tucil3_13522070.git">Report Bug</a>
    ·
    <a href="https://github.com/zultopia/Tucil3_13522070.git">Request Feature</a>
<br>
<br>

[![MIT License][license-shield]][license-url]

  </p>
</div>

<!-- CONTRIBUTOR -->
<div align="center" id="contributor">
  <strong>
    <h3>Dibuat oleh :</h3>
    <table align="center">
      <tr>
        <td>NIM</td>
        <td>Nama</td>
      </tr>
     <tr>
        <td>Marzuli Suhada M</td>
        <td>13522070</td>
    </tr>
    </table>
  </strong>
</div>

## Deskripsi Program

Word ladder adalah permainan kata yang terkenal dimainkan oleh banyak orang. Ditemukan oleh Lewis Carroll pada tahun 1877, permainan ini melibatkan dua kata, yaitu start word dan end word. Pemain harus menemukan rantai kata yang menghubungkan kedua kata tersebut, di mana setiap kata dalam rantai hanya boleh berbeda satu huruf dari kata sebelumnya. Solusi optimal adalah solusi dengan jumlah kata yang dimasukkan ke rantai paling sedikit. 

Program berikut digunakan untuk mencari solusi dari permainan seperti website Word Ladder namun perbedaannya program berikut akan menyajikan 3 algoritma pilihan dalam route planning yaitu UCS, Greedy Best-First Search dan A*. Program ini dibuat dalam bahasa java.

Dokumentasi lengkap tentang program dapat dilihat pada [link berikut](https://docs.google.com/document/d/1tu7SLjJEVwF8sbB8LzhDxNwkIRlGruBIOM_aWvxfHqg/edit)

## Spesifikasi Program

```
Tucil3_13522070
 │ 
 ├── bin    
 ├── doc    
 │        └── Tucil3_13522070.pdf 
 ├── img 
 ├── src    
 │       └─ wordladder
 │       	     ├─ dictionary
 │       	     ├─ image
 │       	     ├─ jafafx-sdk-22.0.1
 │       	     ├─ AStar.java
 │       	     ├─ Dictionary.java
 │       	     ├─ GBFS.java
 │       	     ├─ GUI.java
 │       	     ├─ GUI2.java
 │       	     ├─ Main.java
 │       	     ├─ Node.java
 │       	     ├─ Result.java
 │       	     ├─ UCS.java
 │       	     ├─ Utility.java
 │       	     └─ WordDictionary.java
 ├── test    
 └── README.md  
```

## Requirements

1. javac
2. npm
3. yarn

## Setup and Compilation

1. Clone repo

```
git clone git@github.com:zultopia/Tucil3_13522070.git
```

2. Compile the program

```
cd src/gui
npm install
npm run build
npm start
```

3. Run the program

```
cd ../wordladder
go run .
```

4. Open in Browser

```
http://localhost:3000/
```

<!-- LICENSE -->
## Licensing

The code in this project is licensed under MIT license.  
Code dalam projek ini berada di bawah lisensi MIT.

<br>
<h3 align="center"> TERIMA KASIH! </h3>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/zultopia/Tucil3_13522070/blob/main/LICENSE