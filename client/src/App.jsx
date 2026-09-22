import { useEffect, useState } from 'react'
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';

import './App.css'
import Typography from '@mui/material/Typography';
import Stack from '@mui/material/Stack';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import TextField from '@mui/material/TextField';
import { Outlet, useNavigate } from 'react-router-dom';

function App() {

  const [tab, setTab] = useState(1)
  const navigate = useNavigate()
  console.log(tab)

  const handleChange = (event, newValue) => {
    setTab(newValue);
    console.log(`new value: ${tab}`)
    if(newValue === 1) {
      navigate("/chat")
    }
    if(newValue === 2) {
      navigate("/image")
    }

    if (newValue === 3){
      navigate("/transcription")
    }
  };

  useEffect(() => {
    navigate("/chat")
  }, [])

  return (
    <Box sx={{p:2}}>
      <Typography variant='h6' sx={{mb:3}}>Spring AI</Typography>
        <Tabs centered value={tab} onChange={handleChange} sx={{mb:3}}>
          <Tab label='Chat' value={1}/>
          <Tab label='Imagem' value={2}/>
          <Tab label='Transcrição de audio' value={3}/>
        </Tabs>
        <Outlet/>
    </Box>
  )
}

export default App
