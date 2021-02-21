import React, { useEffect } from 'react'
import ProcessedImage from './ProcessedImage'

export default function ProcessedImagesList({expectedImages, receivedImages}) {
    
    return (
        <div>            
            {expectedImages.map(image => 
                    <ProcessedImage key={image.name} expectedImage={image} receivedImage={receivedImages.find(received => received.name === image.name)}/>
                )
            }
        </div>
    )
}
