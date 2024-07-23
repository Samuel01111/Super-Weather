#!/bin/sh
echo "$FIREBASE_GOOGLE_SERVICE" > app/google-services.json
chmod +x scripts/decode_google_services.sh