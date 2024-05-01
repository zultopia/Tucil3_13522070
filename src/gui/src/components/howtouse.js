import React from 'react';
import './styles.css'; 
import babyFootprint from './assets/baby_footprint.png';

const HowToUse = () => {
    return (
        <div className="how-to-use-container">
            <h1 className="how-to-use-title">How To Use</h1> 
            
            <div className="steps-container"> 
                <div className="step-column">
                    <h2 className="step-title">Step ke-1</h2>
                    <p className="step-description">Klik tombol Game di kanan atas untuk diarahkan ke game word azul</p>
                </div>

                <img src={babyFootprint} alt="Footprint" className="step-separator" />

                <div className="step-column">
                    <h2 className="step-title">Step ke-2</h2>
                    <p className="step-description">Masukkan start word dan end word yang diinginkan</p>
                </div>

                <img src={babyFootprint} alt="Footprint" className="step-separator" />

                <div className="step-column">
                    <h2 className="step-title">Step ke-3</h2>
                    <p className="step-description">Pilih algoritma route planning yang ingin digunakan</p>
                </div>

                <img src={babyFootprint} alt="Footprint" className="step-separator" />

                <div className="step-column">
                    <h2 className="step-title">Step ke-4</h2>
                    <p className="step-description">Klik tombol search lalu hasilnya akan segera muncul di bagian bawah</p>
                </div>
            </div>
        </div>
    );
};

export default HowToUse;