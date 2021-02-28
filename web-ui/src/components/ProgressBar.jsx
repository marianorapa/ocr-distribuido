import React from 'react'

export default function ProgressBar({status}) {
    return (
        <div>
            <div className="progress">
                <div className={`progress-bar progress-bar-striped ${status === 100 ? "bg-success": "progress-bar-animated"}`} 
                        role="progressbar" style={{width: status+"%"}} aria-valuenow={status} aria-valuemin="0" aria-valuemax="100">
                    {status > 5 ? `${status}%` : ""}
                </div>         
            </div>
        </div>
    )
}
