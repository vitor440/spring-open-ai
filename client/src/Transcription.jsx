import Box from '@mui/material/Box'
import Button from '@mui/material/Button'
import CircularProgress from '@mui/material/CircularProgress'
import FilledInput from '@mui/material/FilledInput'
import Input from '@mui/material/Input'
import Typography from '@mui/material/Typography'
import React, { useState } from 'react'
import api from './service/api'

const Transcription = () => {

  const [response, setResponse] = useState(null)
  const [loading, setLoading] = useState(false)
  const [file, setFile] = useState(null)
  console.log(file)

  const handleClick = async () => {
    setLoading(true)
    try {
      const formData = new FormData()
      formData.append("file", file)
      const result = await api.post("/ai/audio/transcription", formData)
      const data = result.data

      setResponse(data)
    } catch (error) {
      
    }
    setLoading(false)
  }

  return (
    <Box sx={{display:"flex", alignItems:"center", flexDirection:"column"}}>
      <Typography variant='h6' sx={{mb:2}}>Transcrição de audio</Typography>
      <Input type='file' sx={{width:"50%", height:"80px"}} onChange={(e) => setFile(e.target.files[0])}/>
          <Button sx={{mt:2, width:130}} variant='contained'  disabled={loading && true} onClick={handleClick}>
            {loading?  <CircularProgress size={"20px"} color='#fff'/>: "Transcrever"}
          </Button>
          {response &&
              <Box sx={{display:"block", backgroundColor:"#ebe7e7", mt:3, width:"50%", minHeight:"50px"}}>

                
                {response}
              </Box>
            }
    </Box>
  )
}

export default Transcription