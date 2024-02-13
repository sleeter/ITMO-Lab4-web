import {createSlice} from '@reduxjs/toolkit';

const tokenSlice = createSlice({
    name: 'token',
    initialState: {
        token: 'tooooken',
        hits: [],
        r: 1,
        auth: false
    },
    reducers: {
        setToken(state, action) {
            state.token = action.payload
            localStorage.setItem("token", action.payload.toString())
            console.log(state.token)
        },
        clearToken(state, action) {
            state.token = ''
            localStorage.setItem("token", "")
        },
        addHit(state, action) {
            state.hits.push(action.payload)
        },
        clearHits(state, action) {
            state.hits = []
        },
        changeR(state, action) {
            state.r = parseFloat(action.payload)
        },
        authorize(state, action) {
            state.auth = true
            localStorage.setItem("auth", "true")
        },
        logOut(state, action) {
            state.auth = false
            localStorage.setItem("auth", "false")
        }
    },
});

export const {setToken, clearToken, addHit, clearHits, changeR, authorize, logOut} = tokenSlice.actions;

export default tokenSlice.reducer;
