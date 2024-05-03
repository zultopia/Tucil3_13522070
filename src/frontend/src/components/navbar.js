import React from 'react';
import './styles.css'; 
import wordazul from './assets/wordazul.png'; 
import { useNavigate } from 'react-router-dom'; 

const Navbar = () => {
    const navigate = useNavigate(); 

    const handleHome = () => {
        navigate('/'); 
    };

    const handleHowToUse = () => {
        navigate('/howtouse'); 
    };

    const handleAboutMe = () => {
        navigate('/aboutme'); 
    };

    const handleAlgorithm = () => {
        navigate('/algorithm');
    };

    const handleGame = () => {
        navigate('/game'); 
    };

    return (
        <div className="navbar">
            <img 
                src={wordazul} 
                alt="Word Azul" 
                className='wordazul' 
                onClick={handleHome} 
                style={{ cursor: 'pointer' }} 
            />
            <div className="navbar-container">
                <button className="navbar-button" onClick={handleGame}>
                    Game
                </button>
                <button className="navbar-button" onClick={handleHowToUse}>
                    How to Use
                </button>
                <button className="navbar-button" onClick={handleAlgorithm}>
                    Algorithm
                </button>
                <button className="navbar-button" onClick={handleAboutMe}>
                    About Me
                </button>
            </div>
        </div>
    );
};

export default Navbar;