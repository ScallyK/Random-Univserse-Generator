import { useEffect, useState } from "react";

function fetchUniverse() {

    // Holds universe data
    const [universeData, setUniverseData] = useState<any>(null);

    // Runs API call on random universe generator ands sets data
    useEffect(() => {
        fetch("http://localhost:8080/universe/random?saveToDatabase=false") // dev
        // fetch("https://random-universe-generator-latest.onrender.com/universe/random?saveToDatabase=false") // prod
            .then((res) => res.json())
            .then(setUniverseData)
            .catch(console.error);
    }, []);

    if (universeData){
        universeData.id = 1;
    }

    console.log("Fetched Universe Data:", universeData);

    return universeData;
}

export default fetchUniverse;

