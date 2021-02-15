import React from 'react'
import '../styles/navbar.css'

export default function Navbar() {
    return (
        <div className="bg-primary header">
            <div className="container col-12 text-center">
                <a className="text-white border-top border-bottom h2">JAO<span className="p-2 small">(Just Another OCR)</span></a>                
            </div>
        </div>
    )
}