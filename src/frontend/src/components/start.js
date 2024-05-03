import React from 'react';
import './styles.css';
import ladder from './assets/ladder.png'; 
import bossbaby from './assets/bossbaby.png'; 

const Start = () => {
    return (
        <div className="start-layout">
            <div className="text-container">
                <h1 className="main-title">Sharpen your vocabulary skills with this game!</h1> 
                <p className="description">
                    WordAzul adalah platform web yang menggabungkan keseruan permainan kata dengan tantangan teka-teki word ladder.
                    Di sini, kalian ditantang untuk menghubungkan dua kata dengan mengubah satu huruf pada satu waktu, menciptakan rangkaian
                    kata yang menarik dan logis. Setiap perubahan membawa kalian lebih dekat ke tujuan, menawarkan pengalaman yang mendidik dan
                    menghibur. Dengan berbagai tingkat kesulitan, WordAzul cocok untuk semua tingkat keterampilan, dari pemula hingga ahli bahasa.
                    Antarmuka yang ramah pengguna dan komunitas yang aktif membuat platform ini menjadi tempat yang sempurna untuk mengasah
                    keterampilan kosa kata kalian sambil bersenang-senang.
                </p>
            </div>
            <div className="image-container"> 
                <img src={ladder} alt="Ladder" className="image" />
                <img src={bossbaby} alt="Boss Baby" className="image" />
            </div>
        </div>
    );
};

export default Start;