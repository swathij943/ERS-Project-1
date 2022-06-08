import { configureStore } from "@reduxjs/toolkit";

//We would import our reducers from the slices we have to create
import userReducer from "./Slices/UserSlice";
import postsReducer from "./Slices/ReimbursementSlice";


export const store = configureStore({
    reducer: {
        //Are the different reducers for modifying our state
        user: userReducer,
        posts: postsReducer
    }
});

//We must export these two things to make our lives easier later
export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;