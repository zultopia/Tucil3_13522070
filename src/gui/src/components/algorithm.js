import React from 'react';
import './styles.css'; 

const Algorithm = () => {
    return (
        <div className="algorithm-container"> 
            <div className="algorithm-column">
                <h2 className="algorithm-title">Algoritma UCS</h2> 
                <p className="algorithm-description">
                    Uniform Cost Search (UCS) adalah algoritma yang menggunakan pendekatan Breadth-First Search (BFS), 
                    namun dengan biaya yang seragam. Algoritma ini mencari jalur dengan biaya paling rendah antara 
                    titik awal dan titik tujuan.
                </p>
                <h3 className="algorithm-steps-title">Langkah-Langkah:</h3> 
                <ul className="algorithm-steps">
                    <li>Mulai dari titik awal.</li>
                    <li>Telusuri semua tetangga dengan biaya terendah.</li>
                    <li>Pilih node dengan biaya terendah untuk dilanjutkan.</li>
                    <li>Ulangi hingga mencapai titik tujuan.</li>
                </ul>
                <h3 className="algorithm-advantages-title">Kelebihan dan Kekurangan:</h3> 
                <ul className="algorithm-advantages">
                    <li>Kelebihan: Menemukan jalur dengan biaya terendah.</li>
                    <li>Kekurangan: Mungkin lambat pada graf yang besar.</li>
                </ul>
            </div>

            <div className="algorithm-column">
                <h2 className="algorithm-title">Greedy Best-First Search</h2> 
                <p className="algorithm-description">
                    Greedy Best-First Search menggunakan heuristik untuk menentukan node mana yang akan dijelajahi 
                    terlebih dahulu. Algoritma ini tidak memperhitungkan biaya keseluruhan, tetapi fokus pada node 
                    yang terlihat paling dekat dengan tujuan.
                </p>
                <h3 className="algorithm-steps-title">Langkah-Langkah:</h3> 
                <ul className="algorithm-steps">
                    <li>Mulai dari titik awal.</li>
                    <li>Gunakan heuristik untuk mengevaluasi tetangga.</li>
                    <li>Pilih node dengan nilai heuristik terendah.</li>
                    <li>Ulangi hingga mencapai tujuan.</li>
                </ul>
                <h3 className="algorithm-advantages-title">Kelebihan dan Kekurangan:</h3> 
                <ul className="algorithm-advantages">
                    <li>Kelebihan: Cepat pada graf yang sederhana.</li>
                    <li>Kekurangan: Bisa menghasilkan jalur yang tidak optimal.</li>
                </ul>
            </div>

            <div className="algorithm-column">
                <h2 className="algorithm-title">Algoritma A*</h2>
                <p className="algorithm-description">
                    A* Search adalah kombinasi UCS dan Greedy Best-First Search. Algoritma ini menggunakan biaya 
                    total dari titik awal hingga tujuan, memperhitungkan biaya dan heuristik.
                </p>
                <h3 className="algorithm-steps-title">Langkah-Langkah:</h3> 
                <ul className="algorithm-steps">
                    <li>Mulai dari titik awal.</li>
                    <li>Gunakan heuristik untuk mengevaluasi tetangga.</li>
                    <li>Pilih node dengan biaya total terendah.</li>
                    <li>Ulangi hingga mencapai tujuan.</li>
                </ul>
                <h3 className="algorithm-advantages-title">Kelebihan dan Kekurangan:</h3> 
                <ul className="algorithm-advantages">
                    <li>Kelebihan: Biasanya menemukan jalur optimal.</li>
                    <li>Kekurangan: Bisa lebih lambat dari Greedy Best-First Search.</li>
                </ul>
            </div>
        </div>
    );
};

export default Algorithm;