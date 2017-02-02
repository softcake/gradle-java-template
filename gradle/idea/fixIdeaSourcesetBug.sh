#!/usr/bin/env bash
find .idea -name \*Test.iml | xargs perl -p -i -e 's/isTestSource="false"/isTestSource="true"/g'
