import React from 'react'


export default function ProcessedImage({expectedImage, receivedImage}) {
    if (receivedImage) return (
        <div className="row p-2 border-bottom">
            <div className="col-md-4">
                <img src={URL.createObjectURL(expectedImage)} alt={expectedImage.name} className="processedImage"/>
            </div>
            <div className="col-md-8 ">
                <p className="processedImageText">{receivedImage.imageText}</p>
            </div>
        </div>
    )

    return null;
}
