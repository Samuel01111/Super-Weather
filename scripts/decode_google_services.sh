#!/bin/sh
echo "$FIREBASE_GOOGLE_SERVICE" > app/google-services.json
ls -l
cd app
ls -l
cat google-services.json