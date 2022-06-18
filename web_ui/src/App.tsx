import React from 'react';
import Button from "@mui/material/Button";

function App() {

    const handleButton=()=>{
        console.log("Button Clicked");
    }

    return (
        <Button variant="contained" onClick={handleButton}>Hello World</Button>
    );
}

export default App;
