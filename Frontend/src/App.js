import './App.css';
import { BrowserRouter,Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import Home from './components/Home/Home';
import User from './components/User/User';
import Signin from './components/Auth/Signin';
import SignUp from './components/Auth/Signup';

function App() {
    //Browser page ler arasında wrap sağlıyo
    //Switch de seçim için hangisi hangisiyse onu seç vs tarzında mesela / için şuna git /page için buna falan gibi
   //Aşağıda rooter yapılan şey frontend de kullanıcılar / yazdığı zaman gitmesi geereken componenet i işaret etmek
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar>  </Navbar>
        <Routes>
          <Route  path="/" element={<Home/>}/>
          <Route  path="/signin" element={<Signin />}/>
          <Route  path="/signup" element={<SignUp />}/>
          <Route  path="/users/:userid" element={<User/>}/> 
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
