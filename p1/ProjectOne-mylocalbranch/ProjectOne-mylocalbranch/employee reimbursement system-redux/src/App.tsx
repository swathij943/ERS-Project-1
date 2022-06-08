import React from 'react';
import './App.css';

import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';

import {LoginPage} from './Views/LoginPage/LoginPage';
import { FeedPage } from './Views/FeedPage/FeedPage';
import {ProfilePage} from './Views/ProfilePage/ProfilePage';

function App() {
  return (
    <BrowserRouter>
      <Routes >
          <Route path="*" element={<Navigate to="/login" replace />} />
          <Route path="/login" element={<LoginPage />}/>
          <Route path="/feed" element={<FeedPage />}/>
          <Route path="/user/:id" element={<ProfilePage />}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
