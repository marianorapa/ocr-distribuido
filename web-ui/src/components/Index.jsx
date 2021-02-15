import React from 'react'
import ImageLoader from './ImageLoader'

export default function Index() {
    return (
        <div className="container">
            <div className="card shadow mt-5">
                <div className="card-body">
                    <div className="card-title">
                        <h2 className="m-2">OCR de imágenes</h2>
                    </div>                
                    <p>Cargue las imágenes para efectuar un reconocimiento óptico de caracteres</p>
                    <ImageLoader />
                </div>
            </div>
        </div>
    )
}

