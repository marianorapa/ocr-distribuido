import React from 'react'


export default function ProcessedImage({expectedImage, receivedImage}) {
    if (receivedImage) return (
        <div className="row p-2 border">
            <div className="col-md-4">
                <img src={URL.createObjectURL(expectedImage)} alt={expectedImage.name}/>
            </div>
            <div className="col-md-8">
                {receivedImage.text}
            </div>
        </div>
    )

    return null;
}
