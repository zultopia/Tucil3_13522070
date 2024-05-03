import React, { useState, useEffect } from 'react';
import './styles.css'; 
import azul from './assets/azul.png'; 
import azul1 from './assets/azul1.png'; 
import azul2 from './assets/azul2.png'; 

const AboutMe = () => {
    const images = [azul, azul1, azul2]; 
    const [currentImage, setCurrentImage] = useState(0); 

    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentImage((prev) => (prev + 1) % images.length); 
        }, 1000); 

        return () => clearInterval(interval); 
    }, [images.length]); 

    return (
        <div className="about-me-container">
            <h1 className="about-me-title">About Me</h1> 
            
            <div className="photocard"> 
                <h2 className="photocard-title">Azul</h2> 
                <img 
                    src={images[currentImage]} 
                    alt="Azul" 
                    className="photocard-image" 
                /> 
                <div className="photocard-info"> 
                    <p>Nama  : Marzuli Suhada M</p>
                    <p>NIM   : 13522070</p>
                </div>
            </div>

            <blockquote className="quote"> 
                "Computer Science isn't just about machines and code; it's about solving problems and shaping the future" - Azul 
            </blockquote>
        </div>
    );
};

export default AboutMe;